package model.State;

import view.Game;
import view.LanternaGUI;

public class ScoreboardState extends State{
    public ScoreboardState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(Game game) {
        System.out.println("On Scoreboard!");
    }
}
