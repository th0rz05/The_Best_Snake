package model.State;

import com.googlecode.lanterna.screen.TerminalScreen;
import view.LanternaGUI;

public abstract class State {
    LanternaGUI screen;
    abstract void step();
}
