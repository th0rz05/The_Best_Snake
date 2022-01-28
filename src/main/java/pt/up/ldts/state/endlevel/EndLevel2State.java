package pt.up.ldts.state.endlevel;

import pt.up.ldts.gui.LanternaGUI;
import pt.up.ldts.state.EndLevelXState;

public class EndLevel2State extends EndLevelXState {

    public EndLevel2State(LanternaGUI screen, double time) {
        super(screen, time);
        setBackgroundColor("#D97EE3");
        setFilename("src/main/resources/Scoreboards/Level2Scoreboard.txt");
    }

}