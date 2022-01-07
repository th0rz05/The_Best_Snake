package model.State;

import view.LanternaGUI;

public class MultiplayerState extends State{
    public MultiplayerState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step() {
        System.out.println("On Multiplayer!");
    }
}
