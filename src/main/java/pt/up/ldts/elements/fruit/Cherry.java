package pt.up.ldts.elements.fruit;

import pt.up.ldts.general.Position;

public class Cherry extends Fruit {
    public Cherry(Position position) {
        super(position);
        setSymbol("v");
        setColor("#8B0000");
        setVelocity(2);
        setSize(-2);
    }
}
