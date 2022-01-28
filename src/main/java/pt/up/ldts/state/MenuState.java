package pt.up.ldts.state;

import com.googlecode.lanterna.TerminalPosition;
import pt.up.ldts.elements.button.BigButton;
import pt.up.ldts.elements.button.SmallButton;
import pt.up.ldts.general.Game;
import pt.up.ldts.general.Position;
import pt.up.ldts.gui.LanternaGUI;

import java.io.IOException;

public class MenuState extends State {

    public MenuState(LanternaGUI screen) {
        super(screen);
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 6),"  ORIGINAL   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 11)," MULTIPLAYER "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 16),"  CHALLENGE  "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 21)," SCOREBOARD  "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 26),"    RULES    "));
        buttonList.add(new SmallButton(new Position(3, 26),"CREDITS"));
        actualbutton=buttonList.get(0);
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#69B8F9");
        drawAllText("#000097");
        drawButtons();
        screen.getScreen().refresh();
        checkInputButtons(game);
    }

    @Override
    public void drawAllText(String color){
        drawText("THE BEST SNAKE",color,new TerminalPosition((screen.getWidth()/2)-7, 1));
        drawText("Q to exit",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
    }

    @Override
    public void exit(Game game) throws IOException{
        screen.getScreen().stopScreen();
        screen.getScreen().close();
        changeState(game, null);
    }

    @Override
    public void enterState(Game game){
        switch(actualbutton.getText()){
            case "  ORIGINAL   ": changeState(game,new OriginalState(new LanternaGUI(screen.getHeight(), screen.getWidth(),"src/main/resources/fonts/Square-Regular.ttf")));break;
            case " MULTIPLAYER ": changeState(game,new MultiplayerState(new LanternaGUI(screen.getHeight(), screen.getWidth(),"src/main/resources/fonts/Square-Regular.ttf")));break;
            case "  CHALLENGE  ": changeState(game,new ChallengeState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
            case " SCOREBOARD  ": changeState(game,new ScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
            case "    RULES    ": changeState(game,new RulesState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
            case "CREDITS": changeState(game,new CreditsState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
        }
    }
}

