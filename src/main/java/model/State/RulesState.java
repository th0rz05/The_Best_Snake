package model.State;

import view.Game;
import view.LanternaGUI;

public class RulesState extends State{
    public RulesState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(Game game) {
        System.out.println("On Rules!");
    }
}
