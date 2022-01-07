package model.State;

import view.LanternaGUI;

public class ChallengeState extends State{
    public ChallengeState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step() {
        System.out.println("On Challenge!");
    }
}
