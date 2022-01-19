package game;

import com.googlecode.lanterna.graphics.TextGraphics;
import elements.*;
import elements.fruit.*;
import gui.LanternaGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.floor;
import static java.lang.Math.random;

public class Arena implements Drawable {

    int height;
    int width;
    Fruit fruit1;
    Fruit fruit2;
    Door door = new Door(new Position(-1,-1));
    Boolean door_open = false;

    private List<Drawable> elements = new ArrayList<>();
    private List<Snake> snakes = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private  List<Fruit> possibleFruits = new ArrayList<>();

    public List<Drawable> getElements() {
        return elements;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Fruit> getPossibleFruits() {
        return possibleFruits;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public Arena() {
        snakes.add(new Snake(new Position(10,10),"#000000"));
    }

    @Override
    public void draw(TextGraphics screen) {
        for(Drawable drawable:elements){
            drawable.draw(screen);
        }
    }

    public Boolean execute(){
        for(Snake snake:snakes){
            snake.move(height,width);
            if(check_snake_head_collisions()){
                snakes.get(0).set_Alive(false);
                snakes.get(1).set_Alive(false);
                return true;
            }
            if(!door_open){
                checkEatFruits(snake);
            }
            if( door_open && checkChallengeWin()){
                return true;
            }
            if(check_snake_collisions(snake.getSnakeHead().getPosition()) || check_wall_collisions(snake.getSnakeHead().getPosition())){
                snake.set_Alive(false);
                return true;
            }
        }
        return false;
    }

    public void checkEatFruits(Snake snake){
        if(snake.getSnakeHead().getPosition().equals(fruit1.getPosition())){
            snake.eatFruit(fruit1,height,width,maximumGrowingSize(snake));
            addFruits();
        }
        if(snake.getSnakeHead().getPosition().equals(fruit2.getPosition())){
            snake.eatFruit(fruit2,height,width,maximumGrowingSize(snake));
            addFruits();
        }
    }

    public int maximumGrowingSize(Snake s){
        int maxSize = 0;
        Position pos = new Position(s.getSnakeTail().getPosition().getX(),s.getSnakeTail().getPosition().getY());
        for (int i = 1;i<=5;i++){
            pos.setX(pos.getX()-s.getDirectionX());
            pos.setY(pos.getY()-s.getDirectionY());
            for(Wall w:walls){
                if(w.getPosition().equals(pos)){
                    return maxSize;
                }
            }
            maxSize++;
        }
        return maxSize;
    }

    public Boolean check_snake_collisions(Position pos){
        for(Snake snake1 : snakes)
            for(int i = 1; i <snake1.getSnake().size(); i++)
                if(snake1.getSnake().get(i).getPosition().equals(pos))
                    return true;
        return false;
    }

    public boolean check_snake_head_collisions(){
        if(snakes.size()==2 && snakes.get(0).getSnakeHead().getPosition().equals(snakes.get(1).getSnakeHead().getPosition())) {
            return true;
        }
        return false;
    }

    public void addFruits(){
        elements.remove(fruit1);
        elements.remove(fruit2);
        double number1 = floor(random() * possibleFruits.size());
        double number2 = number1;
        while(number2 == number1){
            number2 = floor(random() * possibleFruits.size());
        }
        Fruit f1 = possibleFruits.get(((int) number1));
        Fruit f2 = possibleFruits.get(((int) number2));
        if(f1.getSymbol().equals("?") || f2.getSymbol().equals("?")){
            possibleFruits.remove(possibleFruits.size()-1);
            possibleFruits.add(new Mistery(new Position(0,0)));
        }

        f1.setPosition(new Position((int)floor(random()*(width)),(int)floor(random()*(height))));
        while(check_snake_collisions(f1.getPosition()) || check_wall_collisions(f1.getPosition()))
            f1.setPosition(new Position((int)floor(random()*(width)),(int)floor(random()*(height))));

        f2.setPosition(new Position((int)floor(random()*(width)),(int)floor(random()*(height))));
        while(check_snake_collisions(f2.getPosition()) || f2.getPosition().equals(f1.getPosition()) || check_wall_collisions(f2.getPosition()))
            f2.setPosition(new Position((int)floor(random()*(width)),(int)floor(random()*(height))));

        fruit1 = f1;
        fruit2 = f2;
        elements.add(fruit1);
        elements.add(fruit2);
    }

    public Boolean check_wall_collisions(Position pos){
        Position SnakeHeadPosition = pos;
        for(Wall w : walls)
            if(w.getPosition().equals(SnakeHeadPosition))
                return true;
        return false;
    }

    public void addWall(Wall w){
        walls.add(w);
    }

    public void openDoor(){
        door_open = true;
        elements.add(door);
        elements.remove(fruit1);
        elements.remove(fruit2);
    }

    public boolean checkChallengeWin(){
        return snakes.get(0).getSnakeHead().getPosition().equals(door.getPosition());
    }

}
