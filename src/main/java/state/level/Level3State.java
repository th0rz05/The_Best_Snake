package state.level;

import gui.LanternaGUI;
import state.LevelXState;

public class Level3State extends LevelXState {

    public Level3State(LanternaGUI screen,String filename) {
        super(screen,filename);
        setBackgroundColor("#D26691");
        setFINAL_SIZE(8);
        setLevel(3);
    }

}