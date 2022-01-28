package pt.up.ldts.state.level;

import pt.up.ldts.gui.LanternaGUI;
import pt.up.ldts.state.LevelXState;

public class Level3State extends LevelXState {

    public Level3State(LanternaGUI screen, String filename) {
        super(screen,filename);
        setBackgroundColor("#D26691");
        setFINAL_SIZE(8);
        setLevel(3);
    }

}