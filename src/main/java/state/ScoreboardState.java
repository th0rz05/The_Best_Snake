package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import elements.button.BigButton;
import elements.button.Button;
import elements.button.SmallButton;
import observer.KeyboardObserver;
import game.Position;
import game.Game;
import gui.LanternaGUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class ScoreboardState extends State{

    public ScoreboardState(LanternaGUI screen) {
        super(screen);
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 4),"  ORIGINAL   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 9)," MULTIPLAYER "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 14),"    LEVEL1   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 19),"    LEVEL2   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 24),"    LEVEL3   "));
        buttonList.add(new SmallButton(new Position(3, 26)," RESET "));
        actualbutton=buttonList.get(0);
    }

    public void step(Game game) throws IOException{
        screen.getScreen().clear();
        drawBackground("#31B2D8");
        drawAllText("#000097");
        drawButtons();
        screen.getScreen().refresh();
        checkInputButtons(game);
    }


    public void drawAllText(String color){
        drawText("SCOREBOARD",color,new TerminalPosition((screen.getWidth()/2)-5, 1));
        drawText("Q to exit",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
    }


    public void resetFiles() throws FileNotFoundException {
        PrintWriter pw1 = new PrintWriter("src/main/resources/Scoreboards/OriginalScoreBoard.txt");
        PrintWriter pw2 = new PrintWriter("src/main/resources/Scoreboards/MultiplayerScoreBoard.txt");
        PrintWriter pw3 = new PrintWriter("src/main/resources/Scoreboards/Level1ScoreBoard.txt");
        PrintWriter pw4 = new PrintWriter("src/main/resources/Scoreboards/Level2ScoreBoard.txt");
        PrintWriter pw5 = new PrintWriter("src/main/resources/Scoreboards/Level3ScoreBoard.txt");
        pw1.close();pw2.close();pw3.close();pw4.close();pw5.close();
    }

    public void exit(Game game) throws IOException{
        returnMenu(game);
    }

    public void enterState(Game game) throws FileNotFoundException{
        switch(actualbutton.getText()){
            case "  ORIGINAL   ": changeState(game,new ShowScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Scoreboards/OriginalScoreBoard.txt"));break;
            case " MULTIPLAYER ": changeState(game,new ShowScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Scoreboards/MultiplayerScoreBoard.txt"));break;
            case "    LEVEL1   ": changeState(game,new ShowScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Scoreboards/Level1ScoreBoard.txt"));break;
            case "    LEVEL2   ": changeState(game,new ShowScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Scoreboards/Level2ScoreBoard.txt"));break;
            case "    LEVEL3   ": changeState(game,new ShowScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Scoreboards/Level3ScoreBoard.txt"));break;
            case " RESET ": resetFiles();changeState(game,new ScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
        }
    }
}
