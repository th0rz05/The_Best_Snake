package model.State;

import view.Game;
import view.LanternaGUI;

public class ChallengeState extends State{
    public ChallengeState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(Game game) {
        System.out.println("On Challenge!");
    }
}
