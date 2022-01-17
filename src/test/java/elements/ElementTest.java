package elements;

import game.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gui.LanternaGUI;

import java.io.IOException;

public class ElementTest {
    LanternaGUI screen;
    Element element;
    @BeforeEach
    public void setup() throws IOException {
        screen = new LanternaGUI(100,100);
        screen.getScreen().startScreen();
        element = new Element(new Position(10, 10));
        element.setSymbol("T");
    }

    @Test
    public void test_draw(){
        element.draw(screen.getGraphics());
        Assertions.assertEquals(element.getSymbol(),screen.getScreen().getBackCharacter(10,10).getCharacterString());
    }
    @Test
    public void test_draw2(){
        element.draw(screen.getGraphics());
        Assertions.assertNotEquals(element.getSymbol(),screen.getScreen().getBackCharacter(20,20).getCharacterString());
    }
}