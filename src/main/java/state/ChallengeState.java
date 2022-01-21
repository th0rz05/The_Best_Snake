package state;

import com.googlecode.lanterna.TerminalPosition;
import elements.button.BigButton;
import general.Position;
import general.Game;
import gui.LanternaGUI;
import state.level.Level1State;
import state.level.Level2State;
import state.level.Level3State;

import java.io.IOException;

public class ChallengeState extends State{

    public ChallengeState(LanternaGUI screen) {
        super(screen);
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 6),"   LEVEL 1   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 11),"   LEVEL 2   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 16),"   LEVEL 3   "));
        actualbutton=buttonList.get(0);
    }

    @Override
    public void step(Game game) throws IOException{
        screen.getScreen().clear();
        drawBackground("#EFB361");
        drawAllText("#000097");
        drawButtons();
        screen.getScreen().refresh();
        checkInputButtons(game);
    }

    @Override
    public void drawAllText(String color){
        drawText("CHALLENGE MODE",color,new TerminalPosition((screen.getWidth()/2)-7, 1));
        drawText("Q to exit",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
    }

    @Override
    public void exit(Game game) throws IOException{
        returnMenu(game);
    }
    @Override
    public void enterState(Game game){
        switch(actualbutton.getText()){
            case "   LEVEL 1   ": changeState(game,new Level1State(new LanternaGUI(screen.getHeight(), screen.getWidth(),"src/main/resources/fonts/Square-Regular.ttf"),"src/main/resources/Challenge/Level1.txt"));break;
            case "   LEVEL 2   ": changeState(game,new Level2State(new LanternaGUI(screen.getHeight(), screen.getWidth(),"src/main/resources/fonts/Square-Regular.ttf"),"src/main/resources/Challenge/Level2.txt"));break;
            case "   LEVEL 3   ": changeState(game,new Level3State(new LanternaGUI(screen.getHeight(), screen.getWidth(),"src/main/resources/fonts/Square-Regular.ttf"),"src/main/resources/Challenge/Level3.txt"));break;
            }
    }
}
