package model.element.fruit;

import model.Position;

public class Peach extends Fruit {
    public Peach(Position position) {
        super(position);
        setSymbol("p");
        setColor("#ff7f50");
        setVelocity(0.5);
        setSize(-3);
    }
}