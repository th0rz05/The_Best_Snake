import game.Game;
import gui.LanternaGUI;
import state.MenuState;

public class Application {
    public static void main(String[] args) {
        Game game = new Game(new MenuState(new LanternaGUI(30,50)), 60);
        game.start();
    }
}
