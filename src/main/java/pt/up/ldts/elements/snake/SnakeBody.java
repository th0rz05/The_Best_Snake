package pt.up.ldts.elements.snake;
import pt.up.ldts.elements.Element;
import pt.up.ldts.general.Position;

public class SnakeBody extends Element {

    public SnakeBody(Position position, String color) {
        super(position);
        setSymbol("l");
        setColor(color);
    }
}
