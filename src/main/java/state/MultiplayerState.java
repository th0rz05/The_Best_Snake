package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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
    List<Snake> snakes = new ArrayList<>();

    public MultiplayerState(LanternaGUI screen) {
        super(screen);
        snake = new Snake(new Position(30,5),"#ff0000");
        snake2 = new Snake(new Position(30,25),"#ffff00");
        snakes.add(snake);
        snakes.add(snake2);
        arena = new Arena(snakes,screen);
    }


    @Override
    public void step(Game game) throws  IOException{
        Boolean Game_Over = false;
        screen.getScreen().clear();
        drawBackground("#64DF89");
        drawAllText("#000000");
        arena.draw(screen.getGraphics());
        checkInputPlay(game);
        Game_Over = arena.execute();
        screen.getScreen().refresh();
        if(Game_Over){
            screen.getScreen().stopScreen();
            screen.getScreen().close();
            if(!snake.isAlive() && !snake2.isAlive()){
                changeState(game, new EndMultiplayerState(new LanternaGUI(screen.getHeight(),screen.getWidth()),-1,-1,0));
            }
            else if (!snake.isAlive()){
                changeState(game, new EndMultiplayerState(new LanternaGUI(screen.getHeight(),screen.getWidth()),snake2.getSize()-2, snake.getSize()-2,floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10));
            }
            else{
                changeState(game, new EndMultiplayerState(new LanternaGUI(screen.getHeight(),screen.getWidth()),snake.getSize()-2, snake2.getSize()-2,floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10));
            }

        }
    }


    public void drawAllText(String color){
        drawText("Q to exit",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
        drawText("Snake1: " + (snake.getSize()-2), snake.getBodyColor(), new TerminalPosition(1,screen.getHeight()));
        drawText("Snake2: " + (snake2.getSize()-2),snake2.getBodyColor(),new TerminalPosition(12,screen.getHeight()));
        drawText(" | Timer: " + (floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10) + "s",color,new TerminalPosition(21,screen.getHeight()));
        for(int i = 0; i<screen.getWidth();i++){
            screen.getGraphics().putString(new TerminalPosition(i, screen.getHeight()-1),"_");
        }
    }

    public void checkAction(Game game, KeyStroke key) throws IOException{
        if(key.getKeyType()==KeyType.Character) {
            switch (key.getCharacter().toString().toLowerCase()) {
                case ("q"): returnMenu(game);break;
                case ("p"): pause();break;
            }
        }
    }

}


