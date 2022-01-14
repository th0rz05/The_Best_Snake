package controller;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Position;
import model.State.State;
import model.element.Drawable;

public class Button implements Drawable {

    Position position;
    int height = 3;
    int width = 15;
    String text;
    boolean highlight = false;
    String color = "#FFFFFF";
    State state;

    public Button(Position position, String text) {
        this.position = position;
        this.text = text;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public State getState(){
        return state;
    }

    public void draw(TextGraphics screen) {
        if(highlight){
            color = "#F9F204";
        }
        else{
            color = "#FFFFFF";
        }
        screen.setForegroundColor(TextColor.Factory.fromString(color));
        screen.putString(new TerminalPosition(position.getX()+1, position.getY() + 1), text);
        screen.drawRectangle(new TerminalPosition(position.getX(), position.getY()),new TerminalSize(width,height),'=');
    }
}
