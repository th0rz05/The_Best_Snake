package model.State;

import view.LanternaGUI;

public class OriginalState extends State {
    public OriginalState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(){
        System.out.println("On Original!");

    }
}
