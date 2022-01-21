package elements.fruit;

import general.Position;
import static java.lang.Math.floor;
import static java.lang.Math.random;

public class Mistery extends Fruit{

    public Mistery(Position position) {
        super(position);
        setSymbol("x");
        setColor("#F1CE1B");
        int s = (int)floor(random());
        if(s == 0)
            setSize((int)floor(random()*4)+1);
        else
            setSize((int)floor(random()*(-4)-1));
        int v = (int)floor(random()*2);
        if(v == 0)
            setVelocity(2);
        else if(v==1){
            setVelocity(1);
        }
        else{
            setVelocity(0.5);
        }
    }

}
