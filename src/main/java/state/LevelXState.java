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
import state.endlevel.EndLevel1State;
import state.endlevel.EndLevel2State;
import state.endlevel.EndLevel3State;

import java.io.IOException;

import static java.lang.Math.floor;

public abstract class LevelXState extends State {

    Arena arena;
    Snake snake;
    KeyboardObserver observer;
    long startTime;
    long pauseTime;
    int FINAL_SIZE;
    String backgroundColor;
    int level;

    public LevelXState(LanternaGUI screen,String filename) {
        super(screen);
        snake = new Snake(new Position(30,15));
        arena = new Arena(snake,screen);
        arena.buildWalls(filename);
        arena.buildDoor(new Position(screen.getWidth()-1, 10));
        observer = new KeyboardObserver(screen);
        startTime = System.currentTimeMillis();
        pauseTime = 0;
    }

    public void setFINAL_SIZE(int FINAL_SIZE) {
        this.FINAL_SIZE = FINAL_SIZE;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void step(Game game) throws IOException {
        Boolean Game_Over = false;
        screen.getScreen().clear();
        drawBackground(backgroundColor);
        drawAllText("#000000");
        arena.draw(screen.getGraphics());
        checkInput(game);
        Game_Over = arena.execute();
        checkForFinalSize();
        screen.getScreen().refresh();
        if(Game_Over){
            if(arena.checkChallengeWin()){
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                switch(level){
                    case 1 : changeState(game, new EndLevel1State(new LanternaGUI(screen.getHeight(), screen.getWidth()),(floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10)));break;
                    case 2 : changeState(game, new EndLevel2State(new LanternaGUI(screen.getHeight(), screen.getWidth()),(floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10)));break;
                    case 3 : changeState(game, new EndLevel3State(new LanternaGUI(screen.getHeight(), screen.getWidth()),(floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10)));break;
                }
            }
            else{
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                changeState(game, new ChallengeState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
            }
        }
    }

    public void drawText(String text, String color, TerminalPosition position){
        screen.getGraphics().setForegroundColor(TextColor.Factory.fromString(color));
        screen.getGraphics().putString(position, text);
    }

    public void drawAllText(String color){
        drawText("Press Q to exit",color,new TerminalPosition(screen.getWidth()-15, screen.getHeight()));
        drawText("Score: " + (snake.getSize()-2),color,new TerminalPosition(1,screen.getHeight()));
        drawText("|  Timer: " + (floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10) + "s",color,new TerminalPosition(12,screen.getHeight()));
    }

    public void drawBackground(String color){
        screen.getGraphics().setBackgroundColor(TextColor.Factory.fromString(color));
        for (int i = 0;i<screen.getWidth();i++){
            for (int j = 0;j<=screen.getHeight();j++)
                screen.getGraphics().putString(new TerminalPosition(i,j), " ");
        }
    }

    public void checkInput(Game game) throws IOException{
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            checkMovement(key);
            checkAction(game,key);
        }
    }

    public void checkMovement(KeyStroke key){
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

    public void checkAction(Game game, KeyStroke key) throws IOException{
        if(key.getKeyType()== KeyType.Character) {
            switch (key.getCharacter().toString().toLowerCase()) {
                case ("q"): {
                    try {
                        screen.getScreen().stopScreen();
                        screen.getScreen().close();
                        changeState(game, new ChallengeState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }break;
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
            drawText("Score: " + (snake.getSize()-2),"#FFFFFF",new TerminalPosition(1,screen.getHeight()));
            drawText("|  Timer: " + (floor(((initialTime-startTime-pauseTime)/1000f)*10)/10) + "s","#FFFFFF",new TerminalPosition(12,screen.getHeight()));
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

    public void checkForFinalSize(){
        if(snake.getSize()-2 >= FINAL_SIZE){
            arena.openDoor();
        }
    }
}