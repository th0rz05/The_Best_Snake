package model.element.snake;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class SnakeBodyTest {
    TerminalScreen screen;
    SnakeBody snakeBody;
    TextGraphics graphics;
    @BeforeEach
    public void setup() throws IOException {
        TerminalSize terminalSize = new TerminalSize(100, 100);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        snakeBody = new SnakeBody(new Position(10,10));
        graphics = screen.newTextGraphics();
    }

    @Test
    public void test_draw(){
        snakeBody.draw(graphics);
        Assertions.assertEquals(snakeBody.getSymbol(),screen.getBackCharacter(10,10).getCharacterString());
    }

    @Test
    public void test_draw2(){

        snakeBody.draw(graphics);
        Assertions.assertNotEquals(snakeBody.getSymbol(),screen.getBackCharacter(20,20).getCharacterString());
    }
}
