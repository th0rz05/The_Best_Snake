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
import state.endlevel.EndLevel1State;
import state.endlevel.EndLevel2State;
import state.endlevel.EndLevel3State;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

public abstract class LevelXState extends State {

    Arena arena;
    ArenaBuilder arenaBuilder;
    List<Snake> snakeList = new ArrayList<>();
    int FINAL_SIZE;
    String backgroundColor;
    int level;
    Position doorPosition = new Position(7,29);

    public LevelXState(LanternaGUI screen,String filename) {
        super(screen);
        snake = new Snake(new Position(30,15),"#2CFF00");
        snakeList.add(snake);
        arenaBuilder = new ArenaBuilder(snakeList,screen);
        arenaBuilder.buildWalls(filename);
        arenaBuilder.buildDoor(doorPosition);
        arena = arenaBuilder.getArena();
        arena.addFruits();
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
        Boolean GameOver;
        screen.getScreen().clear();
        drawBackground(backgroundColor);
        drawAllText("#000000");
        arena.draw(screen.getGraphics());
        checkInputPlay(game);
        GameOver = arena.execute();
        checkForFinalSize();
        screen.getScreen().refresh();
        if(GameOver){
            if(arena.checkChallengeWin()){
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                switch(level){
                    case 1 : changeState(game, new EndLevel1State(new LanternaGUI(screen.getHeight(), screen.getWidth()),(floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10)));break;
                    case 2 : changeState(game, new EndLevel2State(new LanternaGUI(screen.getHeight(), screen.getWidth()),(floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10)));break;
                    case 3 : changeState(game, new EndLevel3State(new LanternaGUI(screen.getHeight(), screen.getWidth()),(floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10)));break;
                }
            }
            else{returnChallenge(game);}
        }
    }

    public void drawAllText(String color){
        drawText("Q TO EXIT",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
        drawText("SCORE: " + (snake.getSize()-2),color,new TerminalPosition(1,screen.getHeight()));
        drawText("|  TIMER: " + (floor(((System.currentTimeMillis()-startTime-pauseTime)/1000f)*10)/10) + "S",color,new TerminalPosition(12,screen.getHeight()));
    }


    public void checkAction(Game game, KeyStroke key) throws IOException{
        if(key.getKeyType()== KeyType.Character) {
            switch (key.getCharacter().toString().toLowerCase()) {
                case ("q"): returnChallenge(game);break;
                case ("p"): pause();break;
            }
        }
    }

    public void checkForFinalSize(){
        if(snake.getSize()-2 >= FINAL_SIZE){
            arena.openDoor();
        }
    }
}