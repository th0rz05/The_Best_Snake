package elements.fruit;

import game.Position;
import static java.lang.Math.floor;
import static java.lang.Math.random;

public class Mistery extends Fruit{

    public Mistery(Position position) {
        super(position);
        setSymbol("?");
        setColor("#F1CE1B");
        int a = (int)floor(random());
        if(a == 0)
            setSize((int)floor(random()*5));
        else
            setSize((int)floor(random()*(-5)));
        setVelocity(1);
        //setVelocity((int)floor(random()*2 +1));
    }

}
