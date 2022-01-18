package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import game.Game;
import gui.LanternaGUI;
import observer.KeyboardObserver;

import java.io.IOException;

public abstract class State {
    LanternaGUI screen;
    KeyboardObserver observer;

    public State(LanternaGUI screen) {
        this.screen = screen;
        observer = new KeyboardObserver(screen);
    }

    abstract public void step(Game game) throws IOException;

    public void changeState(Game game, State newState) {
        game.setGameState(newState);
    }

    public void drawText(String text, String color, TerminalPosition position){
        screen.getGraphics().setForegroundColor(TextColor.Factory.fromString(color));
        screen.getGraphics().putString(position, text);
    }

    public void drawBackground(String color){
        screen.getGraphics().setBackgroundColor(TextColor.Factory.fromString(color));
        for (int i = 0;i<screen.getWidth();i++){
            for (int j = 0;j<=screen.getHeight();j++)
                screen.getGraphics().putString(new TerminalPosition(i,j), " ");
        }
    }

    public void returnMenu(Game game) throws IOException{
        screen.getScreen().stopScreen();
        screen.getScreen().close();
        changeState(game, new MenuState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
    }

}