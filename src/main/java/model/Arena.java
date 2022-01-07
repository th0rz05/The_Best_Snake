package model;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import controller.KeyboardObserver;
import model.element.Drawable;
import model.element.Element;
import model.element.Snake;
import view.LanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Arena implements Drawable {

    KeyboardObserver observer; //este observer devia ir para o original
    int height;
    int width;
    LanternaGUI screen;

    private List<Drawable> elements = new ArrayList<>();
    private List<Snake> snakes = new ArrayList<>();


    public Arena(Snake snake, LanternaGUI screen) {
        height = screen.getHeight();
        width = screen.getWidth();
        elements.add(snake);
        snakes.add(snake);
        observer = new KeyboardObserver(screen);
        this.screen = screen;
    }

    @Override
    public void draw(TextGraphics screen) {
        for(Drawable drawable:elements){
            drawable.draw(screen);
        }
    }

    public void execute(){
        for(Snake snake:snakes){
            if(observer.readinput()){
                KeyStroke key = observer.getKeys().get(0);
                switch (key.getKeyType()) {
                    case ArrowUp    -> snake.changeDirection(0,-1);
                    case ArrowDown  -> snake.changeDirection(0,1);
                    case ArrowLeft  -> snake.changeDirection(-1,0);
                    case ArrowRight -> snake.changeDirection(1,0);
                }
                if(key.getKeyType()== KeyType.Character && key.getCharacter()=='q'){
                    try{
                        screen.getScreen().stopScreen();
                        screen.getScreen().close();
                        System.exit(0);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            }
            snake.move();
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
                snake.getSnakeHead().setPosition(new Position(snake.getSnakeHead().getPosition().getX(),height));
            }
        }
    }
}
