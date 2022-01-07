package model.State;

import view.LanternaGUI;

public class ScoreboardState extends State{
    public ScoreboardState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step() {
        System.out.println("On Scoreboard!");
    }
}
