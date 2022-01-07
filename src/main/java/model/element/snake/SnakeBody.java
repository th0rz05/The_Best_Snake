package model.element.snake;
import model.Position;
import model.element.Element;

public class SnakeBody extends Element {

    public SnakeBody(Position position) {
        super(position);
        setSymbol("X");
        setColor("#00ff00");
    }
}
