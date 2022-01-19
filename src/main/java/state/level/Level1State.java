package state.level;

import game.Position;
import gui.LanternaGUI;
import state.LevelXState;


public class Level1State extends LevelXState {

    public Level1State(LanternaGUI screen,String filename) {
        super(screen,filename);
        setBackgroundColor("#6475DF");
        setFINAL_SIZE(3);
        setLevel(1);
    }

}

