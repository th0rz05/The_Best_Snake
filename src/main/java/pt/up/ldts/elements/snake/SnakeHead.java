package pt.up.ldts.elements.snake;
import pt.up.ldts.elements.Element;
import pt.up.ldts.general.Position;

public class SnakeHead extends Element {

    public SnakeHead(Position position) {
        super(position);
        setSymbol("q");
        setColor("#000000");
    }

}
