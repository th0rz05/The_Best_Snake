package model.State;

import view.LanternaGUI;

public class RulesState extends State{
    public RulesState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step() {
        System.out.println("On Rules!");
    }
}
