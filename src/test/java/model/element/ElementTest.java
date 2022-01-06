package model.element;

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

public class ElementTest {
    TerminalScreen screen;
    Element element;
    TextGraphics graphics;
    @BeforeEach
    public void setup() throws IOException {
        TerminalSize terminalSize = new TerminalSize(100, 100);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        element = new Element(new Position(10, 10));
        element.setSymbol("T");
        graphics = screen.newTextGraphics();
    }

    @Test
    public void test_draw(){
        element.draw(graphics);
        Assertions.assertEquals(element.getSymbol(),screen.getBackCharacter(10,10).getCharacterString());
    }

    @Test
    public void test_draw2(){
        element.draw(graphics);
        Assertions.assertNotEquals(element.getSymbol(),screen.getBackCharacter(20,20).getCharacterString());
    }
}