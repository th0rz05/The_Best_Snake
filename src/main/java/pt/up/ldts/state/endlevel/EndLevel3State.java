package pt.up.ldts.state.endlevel;

import pt.up.ldts.gui.LanternaGUI;
import pt.up.ldts.state.EndLevelXState;

public class EndLevel3State extends EndLevelXState {

    public EndLevel3State(LanternaGUI screen, double time) {
        super(screen, time);
        setBackgroundColor("#D26691");
        setFilename("src/main/resources/Scoreboards/Level3Scoreboard.txt");
    }

}