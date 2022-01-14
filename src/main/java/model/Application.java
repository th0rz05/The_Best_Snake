package model;

import view.Game;
import view.LanternaGUI;
import model.State.MenuState;

public class Application {
    public static void main(String[] args) {
        Game game = new Game(new MenuState(new LanternaGUI(30,50)), 20);
        game.start();
    }
}
