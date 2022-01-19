package elements.snake;
import game.Position;
import elements.Element;

public class SnakeHead extends Element {

    public SnakeHead(Position position) {
        super(position);
        setSymbol("O");
        setColor("#000000");
    }

}
