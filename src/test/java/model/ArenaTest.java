package model;

import model.element.Element;
import model.element.Snake;
import model.element.snake.SnakeBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.LanternaGUI;

import java.util.List;

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
        Element b = new SnakeBody(new Position(10,10));
        List<Element> Coliding_snake = s1.getSnake();
        Coliding_snake.add(b);
        s1.setSnake(Coliding_snake);

        a.check_snake_collisions(s1.getSnakeHead().getPosition());
        Assertions.assertFalse(s1.isAlive());
    }

    @Test
    public void snake_collisions_test2(){
        a.check_snake_collisions(s1.getSnakeHead().getPosition());
        Assertions.assertTrue(s1.isAlive());
    }

}
