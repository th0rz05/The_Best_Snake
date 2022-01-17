package state;

import game.Game;
import gui.LanternaGUI;

public class MultiplayerState extends State{
    public MultiplayerState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(Game game) {
        System.out.println("On Multiplayer!");
    }
}
