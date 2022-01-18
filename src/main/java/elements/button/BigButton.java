package elements.button;

import game.Position;

public class BigButton extends Button {
    public BigButton(Position position, String text) {
        super(position, text);
        setHeight(3);
        setWidth(15);
    }
}
