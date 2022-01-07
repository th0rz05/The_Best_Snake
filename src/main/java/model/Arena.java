package model;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.element.Drawable;
import model.element.Element;
import model.element.Snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Arena implements Drawable {

    int height;
    int width;

    private List<Drawable> elements = new ArrayList<>();
    private List<Snake> snakes = new ArrayList<>();


    public Arena(Snake snake,int h, int w) {
        height = h;
        width = w;
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
            if(snake.getSnakeHead().getPosition().getX()>width){
                snake.getSnakeHead().setPosition(new Position(0,snake.getSnakeHead().getPosition().getY()));
            }
        }
    }
}
