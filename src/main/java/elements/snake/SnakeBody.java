package elements.snake;
import game.Position;
import elements.Element;

public class SnakeBody extends Element {

    public SnakeBody(Position position,String color) {
        super(position);
        setSymbol("l");
        setColor(color);
    }
}
