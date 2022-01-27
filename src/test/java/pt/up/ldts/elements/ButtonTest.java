package pt.up.ldts.elements;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.ldts.elements.button.BigButton;
import pt.up.ldts.general.Position;
import pt.up.ldts.gui.LanternaGUI;

import java.io.IOException;

public class ButtonTest {

    BigButton button;
    LanternaGUI screen;

    @BeforeEach
    public void setup() throws IOException {
        button = new BigButton(new Position(10,10),"test");
        screen = new LanternaGUI(50,50);
        screen.getScreen().startScreen();
    }

    @Test
    public void drawTestNotHighlighted() throws IOException {
        button.draw(screen.getGraphics());
        Assertions.assertEquals(button.getText().substring(0,1),screen.getScreen().getBackCharacter(11,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(1,2),screen.getScreen().getBackCharacter(12,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(2,3),screen.getScreen().getBackCharacter(13,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(3,4),screen.getScreen().getBackCharacter(14,11).getCharacterString());
        Assertions.assertEquals( new TextColor.RGB(255,255,255),screen.getScreen().getBackCharacter(11,11).getForegroundColor());
        screen.getScreen().stopScreen();
        screen.getScreen().close();
    }

    @Test
    public void drawTestHighlighted() throws IOException {
        button.setHighlight(true);
        button.draw(screen.getGraphics());
        Assertions.assertEquals(button.getText().substring(0,1),screen.getScreen().getBackCharacter(11,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(1,2),screen.getScreen().getBackCharacter(12,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(2,3),screen.getScreen().getBackCharacter(13,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(3,4),screen.getScreen().getBackCharacter(14,11).getCharacterString());
        Assertions.assertEquals( new TextColor.RGB(249,242,4),screen.getScreen().getBackCharacter(11,11).getForegroundColor());
        screen.getScreen().stopScreen();
        screen.getScreen().close();
    }
}
