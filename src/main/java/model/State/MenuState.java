package model.State;

import view.LanternaGUI;

public class MenuState extends State {
    public MenuState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step() {
        System.out.println("On Menu!");
    }
}

