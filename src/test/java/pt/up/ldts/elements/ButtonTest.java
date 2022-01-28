package pt.up.ldts.elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.ldts.elements.button.BigButton;
import pt.up.ldts.general.Position;

public class ButtonTest {

    BigButton button;
    TextGraphics graphics;

    @BeforeEach
    public void setup() {
        button = new BigButton(new Position(10,10),"test");
        graphics = Mockito.mock(TextGraphics.class);
    }

    @Test
    public void drawTestNotHighlighted()  {
        button.draw(graphics);
        Assertions.assertEquals("#FFFFFF",button.getColor());
    }

    @Test
    public void drawTestHighlighted() {
        button.setHighlight(true);
        button.draw(graphics);
        Assertions.assertEquals("#F9F204",button.getColor());
    }
}
