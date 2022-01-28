package pt.up.ldts.elements;

import pt.up.ldts.general.Position;

public class Wall extends Element {

    public Wall(Position position) {
        super(position);
        setSymbol("f");
        setColor("#151C4A");
    }
}
