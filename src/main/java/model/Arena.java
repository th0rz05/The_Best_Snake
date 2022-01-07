package model;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.element.Drawable;
import model.element.Element;
import model.element.Snake;

import java.util.ArrayList;
import java.util.List;

public class Arena implements Drawable {

    private List<Drawable> elements = new ArrayList<>();


    public Arena(Snake snake) {
        elements.add(snake);
    }

    @Override
    public void draw(TextGraphics screen) {
        for(Drawable drawable:elements){
            drawable.draw(screen);
        }
    }
}
