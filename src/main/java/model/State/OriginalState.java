package model.State;

import view.Game;
import view.LanternaGUI;

public class OriginalState extends State {
    public OriginalState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(Game game){
        System.out.println("On Original!");

    }
}
