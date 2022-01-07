package model.State;

import com.googlecode.lanterna.screen.TerminalScreen;
import view.LanternaGUI;

public abstract class State {
    LanternaGUI screen;
    abstract public void step();

    public State(LanternaGUI screen) {
        this.screen = screen;
    }
}
