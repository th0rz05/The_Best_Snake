package model.element;

import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SnakeTest {
    Snake s1;
    @BeforeEach
    public void setup(){
        s1 = new Snake(new Position(10,10));
    }
    @Test
    public void move(){
        s1.move();
        Assertions.assertEquals(new Position(11,10),s1.getSnake().get(0).getPosition());
        Assertions.assertEquals(new Position(10,10),s1.getSnake().get(1).getPosition());
    }
}
