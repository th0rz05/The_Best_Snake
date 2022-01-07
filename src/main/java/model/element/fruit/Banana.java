package model.element.fruit;

import model.Position;

public class Banana extends Fruit {
    public Banana(Position position) {
        super(position);
        setSymbol("b");
        setColor("yellow");
        setVelocity(2);
        setSize(5);
    }
}
