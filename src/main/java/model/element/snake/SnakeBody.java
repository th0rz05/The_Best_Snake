package model.element.snake;
import model.Position;

public class SnakeBody extends SnakeParts {

    public SnakeBody(Position position) {
        super(position);
        setSymbol("■");
        setColor("#333366");
    }
}
