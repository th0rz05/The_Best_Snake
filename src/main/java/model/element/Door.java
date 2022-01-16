package model.element;

import model.Position;

public class Door extends Element {

    public Door(Position position) {
        super(position);
        setSymbol("D");
        setColor("#FD6C08");
    }
}
