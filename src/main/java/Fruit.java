import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Fruit implements Element {
    private Position position;
    private float velocity = 1;
    private int size = 1;
    private String color;
    private String symbol;

    public Fruit(Position position, float velocity, int size, String color, String symbol) {
        this.position = position;
        this.velocity = velocity;
        this.size = size;
        this.color = color;
        this.symbol = symbol;
    }
    public Fruit( float velocity, int size, String color, String symbol) {
        this.velocity = velocity;
        this.size = size;
        this.color = color;
        this.symbol = symbol;
    }


    @Override
    public void draw(TextGraphics screen){
        screen.setForegroundColor(TextColor.Factory.fromString(color));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), symbol);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
