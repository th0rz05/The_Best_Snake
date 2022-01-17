package elements.snake;
import game.Position;
import elements.Element;

public class SnakeBody extends Element {

    public SnakeBody(Position position) {
        super(position);
        setSymbol("X");
        setColor("#000000");
    }
}
