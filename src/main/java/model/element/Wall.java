package model.element;

import model.Position;

public class Wall extends Element {

    public Wall(Position position) {
        super(position);
        setSymbol("#");
        setColor("#151C4A");
    }

}
