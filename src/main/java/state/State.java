package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import elements.Snake;
import elements.button.Button;
import game.Game;
import gui.LanternaGUI;
import observer.KeyboardObserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class State {
    LanternaGUI screen;
    KeyboardObserver observer;
    long startTime;
    long pauseTime;
    List<Button> buttonList = new ArrayList<>();
    Button actualbutton;
    Snake snake;
    Snake snake2;
    String name;

    public State(LanternaGUI screen) {
        this.screen = screen;
        observer = new KeyboardObserver(screen);
        startTime = System.currentTimeMillis();
        pauseTime = 0;
    }

    abstract public void step(Game game) throws IOException;

    public void changeState(Game game, State newState) {
        game.setGameState(newState);
    }

    public void drawText(String text, String color, TerminalPosition position){
        screen.getGraphics().setForegroundColor(TextColor.Factory.fromString(color));
        screen.getGraphics().putString(position, text);
    }

    public abstract void drawAllText(String color);

    public void drawBackground(String color){
        screen.getGraphics().setBackgroundColor(TextColor.Factory.fromString(color));
        for (int i = 0;i<screen.getWidth();i++){
            for (int j = 0;j<=screen.getHeight();j++)
                screen.getGraphics().putString(new TerminalPosition(i,j), " ");
        }
    }

    public void drawButtons(){
        for(Button b : buttonList){
            if (b.equals(actualbutton)){
                b.setHighlight(true);
            }
            b.draw(screen.getGraphics());
            b.setHighlight(false);
        }
    }

    public void returnMenu(Game game) throws IOException{
        screen.getScreen().stopScreen();
        screen.getScreen().close();
        changeState(game, new MenuState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
    }

    public void returnChallenge(Game game) throws IOException{
        screen.getScreen().stopScreen();
        screen.getScreen().close();
        changeState(game, new ChallengeState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
    }

    public void pause() throws  IOException{
        long initialTime = System.currentTimeMillis();
        while(true){
            drawText("PAUSE","#FF0000",new TerminalPosition((screen.getWidth()/2)-2, screen.getHeight()/2));
            drawText("PRESS ANY KEY TO CONTINUE","#FFFFFF",new TerminalPosition((screen.getWidth()/2)-12, (screen.getHeight()/2)+3));
            screen.getScreen().refresh();
            if(observer.readinput()){
                KeyStroke key = observer.getKeys().get(0);
                if(key.getKeyType()!= KeyType.EOF){
                    pauseTime += System.currentTimeMillis()-initialTime;
                    break;
                }
            }
        }
    }

    public void checkInputPlay(Game game) throws IOException{
        if(observer.readinput()){
            for(KeyStroke key : observer.getKeys()){
                checkMovement(key);
                checkAction(game,key);
            }
        }
    }

    public void checkInputButtons(Game game) throws IOException {
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()== KeyType.Enter){
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                enterState(game);
            }
            if(key.getKeyType()== KeyType.Character && key.getCharacter().toString().equalsIgnoreCase("q")){
                exit(game);
            }
            if(key.getKeyType()== KeyType.ArrowDown){
                int index = (buttonList.indexOf(actualbutton)+1);
                if(index==buttonList.size()){
                    index = 0;
                }
                actualbutton = buttonList.get(index);
            }
            if(key.getKeyType()== KeyType.ArrowUp){
                int index = (buttonList.indexOf(actualbutton)-1) ;
                if(index==-1){
                    index = buttonList.size()-1;
                }
                actualbutton = buttonList.get(index);
            }
        }
    }

    public void checkInputEndGame(Game game) throws IOException{
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()== KeyType.Character && name.length() <= 10 ){
                name += key.getCharacter().toString().toLowerCase();
            }
            else if(key.getKeyType()== KeyType.Enter){
                saveScore();
                returnMenu(game);
            }
            else if(key.getKeyType()== KeyType.Backspace && name.length() >=1){
                name = name.substring(0,name.length()-1);
            }
        }
    }

    public void saveScore() throws IOException{};

    public void checkMovement(KeyStroke key){
        moveArrows(key);
        moveWasd(key);
    }

    public void moveArrows(KeyStroke key){
        switch (key.getKeyType()) {
            case ArrowUp: {
                if (!(snake.getDirectionX() == 0 && snake.getDirectionY() == 1)) {
                    snake.changeDirection(0, -1);}break;}
            case ArrowDown: {
                if (!(snake.getDirectionX() == 0 && snake.getDirectionY() == -1)) {
                    snake.changeDirection(0, 1);}break;}
            case ArrowLeft: {
                if (!(snake.getDirectionX() == 1 && snake.getDirectionY() == 0)) {
                    snake.changeDirection(-1, 0);}break;}
            case ArrowRight: {
                if (!(snake.getDirectionX() == -1 && snake.getDirectionY() == 0)) {
                    snake.changeDirection(1, 0);}break;}
            }
    }

    public void moveWasd(KeyStroke key){
        if(snake2!=null && key.getKeyType()==KeyType.Character){
            switch (key.getCharacter().toString().toLowerCase()){
                case ("w"): {
                    if (!(snake2.getDirectionX() == 0 && snake2.getDirectionY() == 1)) {
                        snake2.changeDirection(0, -1);}break;}
                case ("s"): {
                    if (!(snake2.getDirectionX() == 0 && snake2.getDirectionY() == -1)) {
                        snake2.changeDirection(0, 1);}break;}
                case ("a"): {
                    if (!(snake2.getDirectionX() == 1 && snake2.getDirectionY() == 0)) {
                        snake2.changeDirection(-1, 0);}break;}
                case ("d"): {
                    if (!(snake2.getDirectionX() == -1 && snake2.getDirectionY() == 0)) {
                        snake2.changeDirection(1, 0);}break;}
            }
        }
    }

    public void checkAction(Game game, KeyStroke key) throws IOException{};

    public void enterState(Game game) throws FileNotFoundException{};

    public void exit(Game game) throws IOException{};

}