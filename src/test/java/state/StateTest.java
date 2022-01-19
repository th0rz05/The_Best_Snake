package state;

import game.Game;
import gui.LanternaGUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class StateTest {
    LanternaGUI screen = new LanternaGUI(1,1);

    @Test
    public void ChangeStateTest1() throws IOException {

        screen.getScreen().startScreen();
        State menu = new MenuState(screen);
        Game game = new Game(menu,60);
        State Scoreboard = new ScoreboardState(screen);
        menu.changeState(game,Scoreboard);
        Assertions.assertEquals(game.getGamestate(), Scoreboard);
        screen.getScreen().stopScreen();
        screen.getScreen().close();

    }

}
