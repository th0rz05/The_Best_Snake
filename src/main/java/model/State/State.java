package model.State;

import view.Game;
import view.LanternaGUI;

public abstract class State {
    LanternaGUI screen;
    abstract public void step(Game game);
    public void changeState(Game game, State newState) { game.setGameState(newState);}
    public State(LanternaGUI screen) { this.screen = screen;}

}