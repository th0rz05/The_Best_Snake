package state;

import com.googlecode.lanterna.TerminalPosition;
import elements.button.BigButton;
import elements.button.SmallButton;
import general.Position;
import general.Game;
import gui.LanternaGUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ScoreboardState extends State{

    public ScoreboardState(LanternaGUI screen) {
        super(screen);
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 6),"  ORIGINAL   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 11)," MULTIPLAYER "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 16),"   LEVEL 1   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 21),"   LEVEL 2   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 26),"   LEVEL 3   "));
        buttonList.add(new SmallButton(new Position(3, 26)," RESET "));
        actualbutton=buttonList.get(0);
    }

    @Override
    public void step(Game game) throws IOException{
        screen.getScreen().clear();
        drawBackground("#7DE1AF");
        drawAllText("#000097");
        drawButtons();
        screen.getScreen().refresh();
        checkInputButtons(game);
    }

    @Override
    public void drawAllText(String color){
        drawText("SCOREBOARD",color,new TerminalPosition((screen.getWidth()/2)-5, 1));
        drawText("Q to exit",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
    }


    public void resetFiles() throws FileNotFoundException{
        try{
            PrintWriter pw1 = new PrintWriter("src/main/resources/Scoreboards/OriginalScoreBoard.txt", UTF_8.name());
            PrintWriter pw2 = new PrintWriter("src/main/resources/Scoreboards/MultiplayerScoreBoard.txt",UTF_8.name());
            PrintWriter pw3 = new PrintWriter("src/main/resources/Scoreboards/Level1ScoreBoard.txt",UTF_8.name());
            PrintWriter pw4 = new PrintWriter("src/main/resources/Scoreboards/Level2ScoreBoard.txt",UTF_8.name());
            PrintWriter pw5 = new PrintWriter("src/main/resources/Scoreboards/Level3ScoreBoard.txt",UTF_8.name());
            pw1.close();pw2.close();pw3.close();pw4.close();pw5.close();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

    }

    public void exit(Game game) throws IOException{
        returnMenu(game);
    }

    public void enterState(Game game) throws FileNotFoundException{
        switch(actualbutton.getText()){
            case "  ORIGINAL   ": changeState(game,new ShowScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Scoreboards/OriginalScoreBoard.txt"));break;
            case " MULTIPLAYER ": changeState(game,new ShowScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Scoreboards/MultiplayerScoreBoard.txt"));break;
            case "   LEVEL 1   ": changeState(game,new ShowScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Scoreboards/Level1ScoreBoard.txt"));break;
            case "   LEVEL 2   ": changeState(game,new ShowScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Scoreboards/Level2ScoreBoard.txt"));break;
            case "   LEVEL 3   ": changeState(game,new ShowScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Scoreboards/Level3ScoreBoard.txt"));break;
            case " RESET ": resetFiles();changeState(game,new ScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
        }
    }
}
