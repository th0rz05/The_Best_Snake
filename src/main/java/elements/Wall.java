package elements;

import game.Position;

public class Wall extends Element {

    public Wall(Position position) {
        super(position);
        setSymbol("#");
        setColor("#151C4A");
    }
}
