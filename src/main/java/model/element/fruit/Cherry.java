package model.element.fruit;

import model.Position;

public class Cherry extends Fruit {
    public Cherry(Position position) {
        super(position);
        setSymbol("c");
        setColor("#8B0000");
        setVelocity(2);
        setSize(-2);
    }
}
