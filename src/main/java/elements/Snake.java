package elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import game.Position;
import elements.fruit.Fruit;
import elements.snake.SnakeBody;
import elements.snake.SnakeHead;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Drawable {
    private List<Element> snake = new ArrayList<>();
    private int velocity;
    private int size;
    private int pace;
    private int directionX;
    private int directionY;
    private int PACE_TO_MOVEMENT = 12;
    private boolean alive;
    String bodyColor;

    public Snake(Position position,String color) {
        alive = true;
        directionX = 1;
        directionY = 0;
        bodyColor = color;
        snake.add(new SnakeHead(new Position(position.getX(), position.getY())));
        snake.add(new SnakeBody(new Position(position.getX()-directionX, position.getY()-directionY),bodyColor));
        size = 2;
        pace = 0;
        velocity = 2;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
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

    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    public Element getSnakeHead(){
        return snake.get(0);
    }

    public Element getSnakeTail(){
        return snake.get(size-1);
    }

    public boolean isAlive() {
        return alive;
    }

    public void set_Alive(boolean alive){
        this.alive = alive;
    }

    @Override
    public void draw(TextGraphics screen) {
        for(Element element:snake){
            element.draw(screen);
        }
    }

    public void changeDirection(int x, int y){
        setDirectionX(x);
        setDirectionY(y);
    }

    public void move(int height,int width){
        pace += velocity;
        if(pace==PACE_TO_MOVEMENT) {
            pace = 0;
            moveSnake(height, width);
        }
    }

    public void moveSnake(int height, int width) {
        Position newposition = checkScreenLimits(new Position(getSnakeHead().getPosition().getX()+directionX,getSnakeHead().getPosition().getY()+directionY),height,width);
        for(Element element:snake){
            Position oldposition = element.getPosition();
            element.setPosition(newposition);
            newposition = oldposition;
        }
    }

    public void eatFruit(Fruit fruit, int height, int width , int maxSize){
        if(size + fruit.getSize()<2){
            while(size>2){
                snake.remove(size-1);
                size--;
            }
        }
        else if(fruit.getSize()<0){
            for(int i = 0;i > fruit.getSize();i--){
                snake.remove(size-1);
                size--;
            }
        }
        else if(fruit.getSize()>0){
            for(int i = 1;i <= fruit.getSize();i++){
                if(i<=maxSize){
                    Position position = checkScreenLimits(new Position(snake.get(size-1).getPosition().getX()-directionX, snake.get(size-1).getPosition().getY()-directionY),height,width);
                    snake.add(new SnakeBody(position,bodyColor));
                    size++;
                }
            }
        }
        velocity *= fruit.getVelocity();
        if(velocity>4){
            velocity=4;
        }
        if(velocity<1){
            velocity = 1;
        }
    }

    public Position checkScreenLimits(Position position,int height,int width){
        if(position.getX()>width){
            return new Position(0,position.getY());
        }
        if(position.getX()<0){
            return new Position(width,position.getY());
        }
        if(position.getY()>height){
            return new Position(position.getX(),0);
        }
        if(position.getY()<0){
            return new Position(position.getX(),height);
        }
        return position;
    }

}
