package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import elements.button.BigButton;
import elements.button.Button;
import game.Position;
import game.Game;
import gui.LanternaGUI;
import state.level.Level1State;
import state.level.Level2State;
import state.level.Level3State;

import java.io.IOException;

public class ChallengeState extends State{

    public ChallengeState(LanternaGUI screen) {
        super(screen);
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 6),"    LEVEL1   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 11),"    LEVEL2   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 16),"    LEVEL3   "));
        actualbutton=buttonList.get(0);
    }

    public void step(Game game) throws IOException{
        screen.getScreen().clear();
        drawBackground("#EFB361");
        drawAllText("#000097");
        drawButtons();
        screen.getScreen().refresh();
        checkInputButtons(game);
    }

    public void drawAllText(String color){
        drawText("CHALLENGE MODE",color,new TerminalPosition((screen.getWidth()/2)-7, 1));
        drawText("Q to exit",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
    }

    public void exit(Game game) throws IOException{
        returnMenu(game);
    }

    public void enterState(Game game){
        switch(actualbutton.getText()){
            case "    LEVEL1   ": changeState(game,new Level1State(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Challenge/Level1.txt"));break;
            case "    LEVEL2   ": changeState(game,new Level2State(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Challenge/Level2.txt"));break;
            case "    LEVEL3   ": changeState(game,new Level3State(new LanternaGUI(screen.getHeight(), screen.getWidth()),"src/main/resources/Challenge/Level3.txt"));break;
            }
    }
}
