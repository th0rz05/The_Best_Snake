package state;

import game.Game;
import gui.LanternaGUI;

public class ScoreboardState extends State{
    public ScoreboardState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(Game game) {
        System.out.println("On Scoreboard!");
    }
}
