package model.State;

import view.Game;
import view.LanternaGUI;

import java.io.IOException;

public abstract class State {
    LanternaGUI screen;
    abstract public void step(Game game) throws IOException;
    public void changeState(Game game, State newState) { game.setGameState(newState);}
    public State(LanternaGUI screen) { this.screen = screen;}

}