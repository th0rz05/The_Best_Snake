package elements.fruit;

import game.Position;

public class Peach extends Fruit {
    public Peach(Position position) {
        super(position);
        setSymbol("i");
        setColor("#E7621A");
        setVelocity(0.5);
        setSize(-3);
    }
}