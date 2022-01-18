package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import observer.KeyboardObserver;
import game.Arena;
import game.Position;
import elements.Snake;
import game.Game;
import gui.LanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

public class MultiplayerState extends State {
    Arena arena;
    Snake snake1,snake2;
    List<Snake> snakes = new ArrayList<>();
    long startTime;
    long pauseTime;

    public MultiplayerState(LanternaGUI screen) {
        super(screen);
        snake1 = new Snake(new Position(30,5),"#ff0000");
        snake2 = new Snake(new Position(30,25),"#ffff00");
        snakes.add(snake1);
        snakes.add(snake2);
        arena = new Arena(snakes,screen);
        startTime = System.currentTimeMillis();
        pauseTime = 0;
    }


    @Override
    public void step(Game game) throws  IOException{
        Boolean Game_Over = false;
        screen.getScreen().clear();
        drawBackground("#64DF89");
        drawAllText("#000000");
        arena.draw(screen.getGraphics());
        checkInput(game);
        Game_Over = arena.execute();
        screen.getScreen().refresh();
        if(Game_Over){
            screen.getScreen().stopScreen();
            screen.getScreen().close();
            if(!snake1.isAlive() && !snake2.isAlive()){
                changeState(game, new EndMultiplayerState(new LanternaGUI(screen.getHeight(),screen.getWidth()),-1,-1,0));
            }
            else if (!snake1.isAlive()){
                changeState(game, new EndMultiplayerState(new LanternaGUI(screen.getHeight(),screen.getWidth()),snake2.getSize()-2, snake1.getSize()-2,floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10));
            }
            else{
                changeState(game, new EndMultiplayerState(new LanternaGUI(screen.getHeight(),screen.getWidth()),snake1.getSize()-2, snake2.getSize()-2,floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10));
            }

        }
    }


    public void drawAllText(String color){
        drawText("Q to exit",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
        drawText("Snake1: " + (snake1.getSize()-2), snake1.getBodyColor(), new TerminalPosition(1,screen.getHeight()));
        drawText("Snake2: " + (snake2.getSize()-2),snake2.getBodyColor(),new TerminalPosition(12,screen.getHeight()));
        drawText(" | Timer: " + (floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10) + "s",color,new TerminalPosition(21,screen.getHeight()));
        for(int i = 0; i<screen.getWidth();i++){
            screen.getGraphics().putString(new TerminalPosition(i, screen.getHeight()-1),"_");
        }
    }


    public void checkInput(Game game) throws IOException{
        if(observer.readinput()){
            for(KeyStroke key : observer.getKeys()){
                checkMovement(key);
                checkAction(game,key);
            }
        }
    }

    public void checkMovement(KeyStroke key){
        switch (key.getKeyType()) {
            case ArrowUp: {
                if (!(snake1.getDirectionX() == 0 && snake1.getDirectionY() == 1)) {
                    snake1.changeDirection(0, -1);}break;}
            case ArrowDown: {
                if (!(snake1.getDirectionX() == 0 && snake1.getDirectionY() == -1)) {
                    snake1.changeDirection(0, 1);}break;}
            case ArrowLeft: {
                if (!(snake1.getDirectionX() == 1 && snake1.getDirectionY() == 0)) {
                    snake1.changeDirection(-1, 0);}break;}
            case ArrowRight: {
                if (!(snake1.getDirectionX() == -1 && snake1.getDirectionY() == 0)) {
                    snake1.changeDirection(1, 0);}break;}
            case Character: {
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
    }

    public void checkAction(Game game, KeyStroke key) throws IOException{
        if(key.getKeyType()==KeyType.Character) {
            switch (key.getCharacter().toString().toLowerCase()) {
                case ("q"): {
                    returnMenu(game);
                    break;
                }
                case ("p"): {
                    pause();
                    break;
                }
            }
        }
    }

    public void pause() throws  IOException{
        long initialTime = System.currentTimeMillis();
        while(true){
            drawText("PAUSE","#FF0000",new TerminalPosition((screen.getWidth()/2)-2, screen.getHeight()/2));
            drawText("Press any key to continue","#FFFFFF",new TerminalPosition((screen.getWidth()/2)-12, (screen.getHeight()/2)+3));
            screen.getScreen().refresh();
            if(observer.readinput()){
                KeyStroke key = observer.getKeys().get(0);
                if(key.getKeyType()!=KeyType.EOF){
                    pauseTime += System.currentTimeMillis()-initialTime;
                    break;
                }
            }
        }
    }
}


