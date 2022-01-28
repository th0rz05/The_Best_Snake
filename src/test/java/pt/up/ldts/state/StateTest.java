package pt.up.ldts.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.ldts.general.Game;
import pt.up.ldts.gui.LanternaGUI;

public class StateTest {
    LanternaGUI screen = Mockito.mock(LanternaGUI.class);

    @Test
    public void ChangeStateTest1(){

        State menu = new MenuState(screen);
        Game game = new Game(menu,60);
        State Scoreboard = new ScoreboardState(screen);
        menu.changeState(game,Scoreboard);
        Assertions.assertEquals(game.getGamestate(), Scoreboard);
    }

}
