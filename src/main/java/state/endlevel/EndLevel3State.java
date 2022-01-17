package state.endlevel;

import gui.LanternaGUI;
import state.EndLevelXState;

public class EndLevel3State extends EndLevelXState {

    public EndLevel3State(LanternaGUI screen, double time) {
        super(screen, time);
        setBackgroundColor("#D26691");
        setFilename("src/main/resources/Scoreboards/Level3Scoreboard.txt");
    }

}