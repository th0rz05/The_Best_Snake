package pt.up.ldts.elements.fruit;


import pt.up.ldts.elements.Element;
import pt.up.ldts.general.Position;


public abstract class Fruit extends Element {
    private double velocity = 1;
    private int size = 1;

    public Fruit(Position position){
        super(position);
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
