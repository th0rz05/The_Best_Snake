package pt.up.ldts.elements.button;

import pt.up.ldts.general.Position;

public class BigButton extends Button {
    public BigButton(Position position, String text) {
        super(position, text);
        setHeight(3);
        setWidth(15);
    }
}
