package model.element.snake;
import model.Position;

public class SnakeHead extends SnakeParts {

    public SnakeHead(Position position) {
        super(position);
        setSymbol("●");
        setColor("#333366");
    }

}
