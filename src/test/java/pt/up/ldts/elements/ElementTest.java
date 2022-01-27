package pt.up.ldts.elements;


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.ldts.general.Position;
import pt.up.ldts.gui.LanternaGUI;


import static org.mockito.Mockito.atLeastOnce;

public class ElementTest {
    LanternaGUI screen;
    Element element;
    TextGraphics graphics;

    @BeforeEach
    public void setup(){
        graphics = Mockito.mock(TextGraphics.class);
        screen = Mockito.mock(LanternaGUI.class);
        Mockito.when(screen.getGraphics()).thenReturn(graphics);
        element = new Element(new Position(10, 10));
        element.setSymbol("T");
    }

    @Test
    public void test_draw() {
        element.draw(screen.getGraphics());
        Mockito.verify(graphics,atLeastOnce()).putString(Mockito.any(TerminalPosition.class),Mockito.anyString());
    }
}