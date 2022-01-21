package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import general.ArenaBuilder;
import general.Arena;
import general.Position;
import elements.Snake;
import general.Game;
import gui.LanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

public class OriginalState extends State {
    Arena arena;
    ArenaBuilder arenaBuilder;
    List<Snake> snakeList = new ArrayList<>();


    public OriginalState(LanternaGUI screen) {
        super(screen);
        snake = new Snake(new Position(30,15),"#544EE7");
        snakeList.add(snake);
        arenaBuilder = new ArenaBuilder(snakeList,screen)
;       arena = arenaBuilder.getArena();
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
            changeState(game, new EndOriginalState(new LanternaGUI(screen.getHeight(),screen.getWidth()),snake.getSize()-2, (floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10)));
        }
    }

    @Override
    public void drawAllText(String color){
        drawText("Q TO EXIT",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
        drawText("SCORE: " + (snake.getSize()-2),color,new TerminalPosition(1,screen.getHeight()));
        drawText("|  TIMER: " + (floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10) + "S",color,new TerminalPosition(12,screen.getHeight()));
        for(int i = 0; i<screen.getWidth();i++){
            screen.getGraphics().putString(new TerminalPosition(i, screen.getHeight()-1),"_");
        }
    }

    @Override
    public void checkAction(Game game, KeyStroke key) throws IOException{
        if(key.getKeyType()==KeyType.Character) {
            switch (key.getCharacter().toString().toLowerCase()) {
                case ("q"): returnMenu(game);break;
                case ("p"): pause();break;
            }
        }
    }

}

