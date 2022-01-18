package elements.button;

import game.Position;

public class SmallButton extends Button{
    public SmallButton(Position position, String text) {
        super(position, text);
        setHeight(3);
        setWidth(9);
    }
}
