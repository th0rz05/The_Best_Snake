package state;

import elements.Snake;
import game.Arena;
import game.Game;
import game.Position;
import gui.LanternaGUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class StateTest {
    LanternaGUI screen = new LanternaGUI(1,1);

    @Test
    public void ChangeStateTest1(){

        State menu = new MenuState(screen);
        Game game = new Game(menu,60);
        State Scoreboard = new ScoreboardState(screen);
        menu.changeState(game,Scoreboard);
        Assertions.assertEquals(game.getGamestate(), Scoreboard);

    }

}
