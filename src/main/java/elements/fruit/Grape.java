package elements.fruit;

import general.Position;

public class Grape extends Fruit {
    public Grape(Position position) {
        super(position);
        setSymbol("b");
        setColor("#A020F0");
        setVelocity(1);
        setSize(2);
    }
}
