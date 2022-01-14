package model;

import model.element.Snake;
import model.element.snake.SnakeBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.LanternaGUI;

public class ArenaTest {
    Snake s1;
    Arena a;
    @BeforeEach
    public void setup(){
        s1 = new Snake(new Position(10,10));
        a = new Arena(s1, new LanternaGUI(50,50));
    }


    @Test
    public void check_snake_collisions_test1(){
        SnakeBody b = new SnakeBody(new Position(10,10));
        a.check_snake_collisions();
        Assertions.assertFalse(s1.isAlive());

    }

    @Test
    public void snake_collisions_test2(){
        SnakeBody b = new SnakeBody(new Position(12,10));
        a.execute();
        Assertions.assertTrue(s1.isAlive());
        a.execute();
        Assertions.assertFalse(s1.isAlive());

    }

}
