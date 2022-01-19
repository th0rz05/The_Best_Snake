package game;

import elements.Element;
import elements.Snake;
import elements.Wall;
import elements.snake.SnakeBody;
import game.Arena;
import game.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gui.LanternaGUI;

import java.util.List;

public class ArenaTest {
    Snake s1;
    Arena a;
    @BeforeEach
    public void setup(){
        s1 = new Snake(new Position(10,10),"#000000");
        a = new Arena(s1, new LanternaGUI(50,50));
    }


    //@Test
    /*public void check_snake_collisions_test1(){
        Element b = new SnakeBody(new Position(10,10),"#000000");
        List<Element> Coliding_snake = s1.getSnake();
        Coliding_snake.add(b);
        s1.setSnake(Coliding_snake);

        a.check_snake_collisions(s1.getSnakeHead().getPosition());
        Assertions.assertFalse(s1.isAlive());
    }*/

    @Test
    public void snake_collisions_test2(){
        a.check_snake_collisions(s1.getSnakeHead().getPosition());
        Assertions.assertTrue(s1.isAlive());
    }

    @Test
    public void notCollidingAgainstWalls(){
        a.addWall(new Wall(new Position(7,10)));
        Assertions.assertEquals(1,a.maximumGrowingSize(s1));
    }

    @Test
    public void notCollidingAgainstWalls2(){
        a.addWall(new Wall(new Position(3,10)));
        Assertions.assertEquals(5,a.maximumGrowingSize(s1));
    }

    @Test
    public void notCollidingAgainstWalls3(){
        a.addWall(new Wall(new Position(2,10)));
        Assertions.assertEquals(5,a.maximumGrowingSize(s1));
    }

}
