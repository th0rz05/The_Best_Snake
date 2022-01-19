package elements.fruit;

import game.Position;

public class Peach extends Fruit {
    public Peach(Position position) {
        super(position);
        setSymbol("o");
        setColor("#FFDAB9");
        setVelocity(0.5);
        setSize(-3);
    }
}