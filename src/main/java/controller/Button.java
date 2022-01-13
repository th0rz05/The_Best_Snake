package controller;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;
import model.element.Drawable;

public class Button implements Drawable {

    Position position;
    int height = 5;
    int width = 12;
    String text;
    boolean highlight = false;
    String color = "#FFFFFF";

    public Button(Position position, String text) {
        this.position = position;
        this.text = text;
        if(text.equals("ORIGINAL")){highlight = true;}
    }

    public void draw(TextGraphics screen) {
        if(highlight){
            color = "#F9F204";
        }
        screen.setForegroundColor(TextColor.Factory.fromString(color));
        screen.putString(new TerminalPosition(position.getX() + 2, position.getY() + 2), text);
        screen.drawRectangle(new TerminalPosition(position.getX(), position.getY()),new TerminalSize(width,height),'=');
    }
}
