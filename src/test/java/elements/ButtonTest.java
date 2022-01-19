package elements;

import com.googlecode.lanterna.TextColor;
import elements.button.BigButton;
import elements.button.Button;
import game.Position;
import gui.LanternaGUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ButtonTest {

    BigButton button;
    LanternaGUI screen;

    @BeforeEach
    public void setup(){
        button = new BigButton(new Position(10,10),"test");
        screen = new LanternaGUI(50,50);
    }

    @Test
    public void drawTestNotHighlighted(){
        button.draw(screen.getGraphics());
        Assertions.assertEquals(button.getText().substring(0,1),screen.getScreen().getBackCharacter(11,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(1,2),screen.getScreen().getBackCharacter(12,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(2,3),screen.getScreen().getBackCharacter(13,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(3,4),screen.getScreen().getBackCharacter(14,11).getCharacterString());
        Assertions.assertEquals( new TextColor.RGB(255,255,255),screen.getScreen().getBackCharacter(11,11).getForegroundColor());
    }

    @Test
    public void drawTestHighlighted(){
        button.setHighlight(true);
        button.draw(screen.getGraphics());
        Assertions.assertEquals(button.getText().substring(0,1),screen.getScreen().getBackCharacter(11,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(1,2),screen.getScreen().getBackCharacter(12,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(2,3),screen.getScreen().getBackCharacter(13,11).getCharacterString());
        Assertions.assertEquals(button.getText().substring(3,4),screen.getScreen().getBackCharacter(14,11).getCharacterString());
        Assertions.assertEquals( new TextColor.RGB(249,242,4),screen.getScreen().getBackCharacter(11,11).getForegroundColor());
    }
}
