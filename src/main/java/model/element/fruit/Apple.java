package model.element.fruit;

import model.Position;

public class Apple extends Fruit {
    public Apple(Position position) {
        super(position);
         setSymbol("m");
         setColor("#ff0000");
         setVelocity(0.5);
         setSize(3);
    }
}
