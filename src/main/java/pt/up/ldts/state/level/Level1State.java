package pt.up.ldts.state.level;

import pt.up.ldts.gui.LanternaGUI;
import pt.up.ldts.state.LevelXState;


public class Level1State extends LevelXState {

    public Level1State(LanternaGUI screen, String filename) {
        super(screen,filename);
        setBackgroundColor("#6475DF");
        setFINAL_SIZE(3);
        setLevel(1);
    }

}

