
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.DefaultTextGUIGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class SnakeHeadTest {
    TerminalScreen screen;
    SnakeHead snakeHead;
    @BeforeEach
    public void setup() throws IOException {
        TerminalSize terminalSize = new TerminalSize(100, 100);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        snakeHead = new SnakeHead(new Position(10,10));
    }

    @Test
    public void test_draw(){
        snakeHead.draw(screen.newTextGraphics());
        Assertions.assertEquals(snakeHead.getSymbol(),screen.getBackCharacter(10,10).getCharacterString());
    }

    @Test
    public void test_draw2(){

        snakeHead.draw(screen.newTextGraphics());
        Assertions.assertNotEquals(snakeHead.getSymbol(),screen.getBackCharacter(20,20).getCharacterString());
    }
}
