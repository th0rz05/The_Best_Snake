package pt.up.ldts.state.endlevel;

import pt.up.ldts.gui.LanternaGUI;
import pt.up.ldts.state.EndLevelXState;

public class EndLevel1State extends EndLevelXState {

    public EndLevel1State(LanternaGUI screen, double time) {
        super(screen, time);
        setBackgroundColor("#6475DF");
        setFilename("src/main/resources/Scoreboards/Level1Scoreboard.txt");
    }

}

