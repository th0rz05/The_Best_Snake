import general.Game;
import general.MusicPlayer;
import gui.LanternaGUI;
import state.MenuState;

public class Application {
    public static void main(String[] args) {
        MusicPlayer mp = new MusicPlayer();
        mp.startMusic();
        Game game = new Game(new MenuState(new LanternaGUI(30,50)), 45);
        game.start();
    }
}
