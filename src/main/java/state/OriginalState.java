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

import static java.lang.Math.floor;

public class OriginalState extends State {
    Arena arena;
    Snake snake;
    long startTime;
    long pauseTime;

    public OriginalState(LanternaGUI screen) {
        super(screen);
        snake = new Snake(new Position(30,15),"#000000");
        arena = new Arena(snake,screen);
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
            changeState(game, new EndOriginalState(new LanternaGUI(screen.getHeight(),screen.getWidth()),snake.getSize()-2, (floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10)));
        }
    }

    public void drawAllText(String color){
        drawText("Q to exit",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
        drawText("Score: " + (snake.getSize()-2),color,new TerminalPosition(1,screen.getHeight()));
        drawText("|  Timer: " + (floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10) + "s",color,new TerminalPosition(12,screen.getHeight()));
        for(int i = 0; i<screen.getWidth();i++){
            screen.getGraphics().putString(new TerminalPosition(i, screen.getHeight()-1),"_");
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
        if(key.getKeyType()==KeyType.Character) {
            switch (key.getCharacter().toString().toLowerCase()) {
                case ("q"): {
                    try {
                        returnMenu(game);
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

