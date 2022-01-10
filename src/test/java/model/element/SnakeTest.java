package model.element;

import model.Position;
import model.element.fruit.Apple;
import model.element.fruit.Kiwi;
import model.element.fruit.Orange;
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
        Assertions.assertEquals(new Position(11,10),s1.getSnakeHead().getPosition());
        Assertions.assertEquals(new Position(10,10),s1.getSnake().get(1).getPosition());
    }

    @Test
    public void changeDirection1(){
        s1.changeDirection(0,1);
        Assertions.assertEquals(0,s1.getDirectionX());
        Assertions.assertEquals(1,s1.getDirectionY());
    }

    @Test
    public void changeDirection2(){
        s1.changeDirection(-1,0);
        Assertions.assertEquals(-1,s1.getDirectionX());
        Assertions.assertEquals(0,s1.getDirectionY());
    }

    @Test
    public void eatApple(){
        s1.eatFruit(new Apple(new Position(0,0)));
        Assertions.assertEquals(5,s1.getSize());
        Assertions.assertEquals(1,s1.getVelocity());
    }

    @Test
    public void eatKiwi(){
        s1.eatFruit(new Kiwi(new Position(0,0)));
        Assertions.assertEquals(2,s1.getSize());
        Assertions.assertEquals(4,s1.getVelocity());
    }

    @Test
    public void eatOrange(){
        s1.eatFruit(new Orange(new Position(0,0)));
        Assertions.assertEquals(3,s1.getSize());
        Assertions.assertEquals(2,s1.getVelocity());
    }
}
