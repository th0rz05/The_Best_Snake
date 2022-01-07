package model.element.fruit;


import model.Position;
import model.element.Element;


abstract class Fruit extends Element {
    private float velocity = 1;
    private int size = 1;

    public Fruit(Position position){
        super(position);
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
