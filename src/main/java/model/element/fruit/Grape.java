package model.element.fruit;

import model.Position;

public class Grape extends Fruit {
    public Grape(Position position) {
        super(position);
        setSymbol("g");
        setColor("#A020F0");
        setVelocity(1);
        setSize(2);
    }
}
