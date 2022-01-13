package model.element.snake;
import model.Position;
import model.element.Element;

public class SnakeHead extends Element {

    public SnakeHead(Position position) {
        super(position);
        setSymbol("O");
        setColor("#040FF9");
    }

}
