package model.element.fruit;

import model.Position;

public class Apple extends Fruit {
    public Apple(Position position) {
        super(position);
         setSymbol("m");
         setColor("red");
         setVelocity(1);
         setSize(3);
    }
}
