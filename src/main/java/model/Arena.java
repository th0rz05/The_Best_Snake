package model;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.element.Drawable;
import model.element.Element;
import model.element.Snake;
import model.element.fruit.*;
import view.LanternaGUI;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;
import static java.lang.Math.random;

public class Arena implements Drawable {

    int height;
    int width;

    private List<Drawable> elements = new ArrayList<>();
    private List<Snake> snakes = new ArrayList<>();
    private List<Fruit> fruits = new ArrayList<>();
    private final List<Fruit> POSSIBLE_FRUITS = new ArrayList<>();

    public Arena(Snake snake, LanternaGUI screen) {
        height = screen.getHeight()-1;
        width = screen.getWidth();
        elements.add(snake);
        snakes.add(snake);
        POSSIBLE_FRUITS.add(new Apple(new Position(0,0)));
        POSSIBLE_FRUITS.add(new Orange(new Position(0,0)));
        POSSIBLE_FRUITS.add(new Kiwi(new Position(0,0)));
        POSSIBLE_FRUITS.add(new Banana(new Position(0,0)));
        addFruits();
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
            if(snake.getSnakeHead().getPosition().equals(fruits.get(0).getPosition())){
                snake.eatFruit(fruits.get(0),height,width);
                elements.remove(fruits.get(0));
                elements.remove(fruits.get(1));
                fruits.clear();
            }
            else if(snake.getSnakeHead().getPosition().equals(fruits.get(1).getPosition())){
                snake.eatFruit(fruits.get(1),height,width);
                elements.remove(fruits.get(0));
                elements.remove(fruits.get(1));
                fruits.clear();
            }
            if(fruits.isEmpty()){
                addFruits();
            }
        }
    }

    public void checkScreenLimits(Snake snake){
        if(snake.getSnakeHead().getPosition().getX()>=width){
            snake.getSnakeHead().setPosition(new Position(0,snake.getSnakeHead().getPosition().getY()));
        }
        if(snake.getSnakeHead().getPosition().getX()<0){
            snake.getSnakeHead().setPosition(new Position(width,snake.getSnakeHead().getPosition().getY()));
        }
        if(snake.getSnakeHead().getPosition().getY()>height){
            snake.getSnakeHead().setPosition(new Position(snake.getSnakeHead().getPosition().getX(),0));
        }
        if(snake.getSnakeHead().getPosition().getY()<0){
            snake.getSnakeHead().setPosition(new Position(snake.getSnakeHead().getPosition().getX(),height));
        }
    }

    public void addFruits(){
        double number1 = floor(random() * POSSIBLE_FRUITS.size());
        double number2 = number1;
        while(number2 == number1){
            number2 = floor(random() * POSSIBLE_FRUITS.size());
        }
        Fruit f1 = POSSIBLE_FRUITS.get(((int) number1));
        Fruit f2 = POSSIBLE_FRUITS.get(((int) number2));
        f1.setPosition(new Position((int)floor(random()*(width)),(int)floor(random()*(height))));
        f2.setPosition(new Position((int)floor(random()*(width)),(int)floor(random()*(height))));
        fruits.add(f1);
        fruits.add(f2);
        elements.add(f1);
        elements.add(f2);
    }

}
