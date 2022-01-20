package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Arena;
import game.ArenaBuilder;
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
    ArenaBuilder arenaBuilder;
    List<Snake> snakes = new ArrayList<>();

    public MultiplayerState(LanternaGUI screen) {
        super(screen);
        snake = new Snake(new Position(15,25),"#FF2E00");
        snake2 = new Snake(new Position(5,5),"#0400FF");
        snakes.add(snake);
        snakes.add(snake2);
        arenaBuilder = new ArenaBuilder(snakes,screen);
        arena = arenaBuilder.getArena();
        arena.addFruits();
    }

    @Override
    public void step(Game game) throws  IOException{
        Boolean GameOver;
        screen.getScreen().clear();
        drawBackground("#85AA89");
        drawAllText("#000000");
        arena.draw(screen.getGraphics());
        checkInputPlay(game);
        GameOver = arena.execute();
        screen.getScreen().refresh();
        if(GameOver){
            screen.getScreen().stopScreen();
            screen.getScreen().close();
            if(!snake.isAlive() && !snake2.isAlive()){
                changeState(game, new EndMultiplayerState(new LanternaGUI(screen.getHeight(),screen.getWidth())));
            }
            else if (!snake.isAlive()){
                changeState(game, new EndMultiplayerState(new LanternaGUI(screen.getHeight(),screen.getWidth()),snake2, snake,floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10));
            }
            else{
                changeState(game, new EndMultiplayerState(new LanternaGUI(screen.getHeight(),screen.getWidth()),snake, snake2,floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10));
            }

        }
    }


    public void drawAllText(String color){
        drawText("Q TO EXIT",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
        drawText("SNAKE2: " + (snake2.getSize()-2), snake2.getBodyColor(), new TerminalPosition(1,screen.getHeight()));
        drawText("SNAKE1: " + (snake.getSize()-2),snake.getBodyColor(),new TerminalPosition(12,screen.getHeight()));
        drawText(" | TIMER: " + (floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10) + "S",color,new TerminalPosition(21,screen.getHeight()));
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


