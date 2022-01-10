package model.element.fruit;

import model.Position;

public class Orange extends Fruit {
    public Orange(Position position) {
        super(position);
        setSymbol("o");
        setColor("#ffbf00");
        setVelocity(1);
        setSize(1);
    }
}
