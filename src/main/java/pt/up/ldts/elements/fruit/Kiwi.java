package pt.up.ldts.elements.fruit;

import pt.up.ldts.general.Position;

public class Kiwi extends Fruit {
    public Kiwi(Position position) {
        super(position);
        setSymbol("k");
        setColor("#046F3A");
        setVelocity(2);
        setSize(-5);
    }
}
