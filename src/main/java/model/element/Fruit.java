package model.element;


import model.Position;


public class Fruit extends Element {
    private float velocity = 1;
    private int size = 1;

    public Fruit(Position position, float velocity, int size, String color, String symbol){
        super(position);
        this.velocity = velocity;
        this.size = size;
        setColor(color);
        setSymbol(symbol);
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
