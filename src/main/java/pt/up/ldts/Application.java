package pt.up.ldts;

import pt.up.ldts.general.Game;
import pt.up.ldts.general.MusicPlayer;
import pt.up.ldts.gui.LanternaGUI;
import pt.up.ldts.state.MenuState;

public class Application {
    public static void main(String[] args) {
        MusicPlayer mp = new MusicPlayer();
        mp.startMusic();
        Game game = new Game(new MenuState(new LanternaGUI(30,50)), 45);
        game.start();

    }
}
