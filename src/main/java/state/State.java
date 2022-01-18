package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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

    public void pause() throws  IOException{
        long initialTime = System.currentTimeMillis();
        while(true){
            drawText("PAUSE","#FF0000",new TerminalPosition((screen.getWidth()/2)-2, screen.getHeight()/2));
            drawText("Press any key to continue","#FFFFFF",new TerminalPosition((screen.getWidth()/2)-12, (screen.getHeight()/2)+3));
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

    public void checkMovement(KeyStroke key){};

    public void checkAction(Game game, KeyStroke key) throws IOException{};

    public void enterState(Game game) throws FileNotFoundException{};

    public void exit(Game game) throws IOException{};

}