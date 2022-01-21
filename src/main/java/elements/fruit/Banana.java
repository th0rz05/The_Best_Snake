package elements.fruit;

import general.Position;

public class Banana extends Fruit {
    public Banana(Position position) {
        super(position);
        setSymbol("j");
        setColor("#ffff00");
        setVelocity(2);
        setSize(5);
    }
}
