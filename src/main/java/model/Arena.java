package model;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.element.Drawable;
import model.element.Snake;
import model.element.fruit.Fruit;
import view.LanternaGUI;

import java.util.ArrayList;
import java.util.List;

public class Arena implements Drawable {

    int height;
    int width;

    private List<Drawable> elements = new ArrayList<>();
    private List<Snake> snakes = new ArrayList<>();
    private List<Fruit> fruits = new ArrayList<>();



    public Arena(Snake snake, LanternaGUI screen) {
        height = screen.getHeight()-1;
        width = screen.getWidth();
        elements.add(snake);
        snakes.add(snake);
    }

    @Override
    public void draw(TextGraphics screen) {
        for(Drawable drawable:elements){
            drawable.draw(screen);
        }
    }

    public void execute(){
        for(Snake snake:snakes){
            snake.move();
            checkScreenLimits(snake);
        }
    }

    public void checkScreenLimits(Snake snake){
        if(snake.getSnakeHead().getPosition().getX()>=width){
            snake.getSnakeHead().setPosition(new Position(0,snake.getSnakeHead().getPosition().getY()));
        }
        if(snake.getSnakeHead().getPosition().getX()<0){
            snake.getSnakeHead().setPosition(new Position(width,snake.getSnakeHead().getPosition().getY()));
        }
        if(snake.getSnakeHead().getPosition().getY()>=height){
            snake.getSnakeHead().setPosition(new Position(snake.getSnakeHead().getPosition().getX(),0));
        }
        if(snake.getSnakeHead().getPosition().getY()<0){
            snake.getSnakeHead().setPosition(new Position(snake.getSnakeHead().getPosition().getX(),height-1));
        }
    }
}
