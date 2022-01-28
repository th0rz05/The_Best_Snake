package pt.up.ldts.elements.fruit;

import pt.up.ldts.general.Position;

public class Apple extends Fruit {
    public Apple(Position position) {
        super(position);
         setSymbol("g");
         setColor("#ff0000");
         setVelocity(0.5);
         setSize(3);
    }
}
