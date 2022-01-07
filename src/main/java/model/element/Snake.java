package model.element;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;
import model.element.snake.SnakeBody;
import model.element.snake.SnakeHead;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Drawable{
    private List<Element> snake = new ArrayList<>();
    private int velocity;
    private int size;
    private int pace;
    private int directionX;
    private int directionY;
    private int PACE_TO_MOVEMENT = 8;


    public Snake(Position position) {
        snake.add(new SnakeHead(new Position(position.getX(), position.getY())));
        snake.add(new SnakeBody(new Position(position.getX()-1, position.getY())));
        size = 2;
        pace = 0;
        directionX = 1;
        directionY = 0;
    }

    public List<Element> getSnake() {
        return snake;
    }

    public void setSnake(List<Element> snake) {
        this.snake = snake;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPace() {
        return pace;
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public int getPACE_TO_MOVEMENT() {
        return PACE_TO_MOVEMENT;
    }

    public void setPACE_TO_MOVEMENT(int PACE_TO_MOVEMENT) {
        this.PACE_TO_MOVEMENT = PACE_TO_MOVEMENT;
    }

    public Element getSnakeHead(){
        return snake.get(0);
    }

    @Override
    public void draw(TextGraphics screen) {
        for(Element element:snake){
            element.draw(screen);
        }
    }

    public void move(){
        Position newposition = new Position(getSnakeHead().getPosition().getX()+directionX,getSnakeHead().getPosition().getY()+directionY);
        for(Element element:snake){
            Position oldposition = element.getPosition();
            element.setPosition(newposition);
            newposition = oldposition;
        }
    }
}
