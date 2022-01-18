package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import observer.KeyboardObserver;
import game.Game;
import gui.LanternaGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class CreditsState extends State{


    public CreditsState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#31B2D8");
        drawAllText("#000097");
        screen.getScreen().refresh();
        checkInput(game);
    }

    public void drawAllText(String color){
        drawText("CREDITS",color,new TerminalPosition((screen.getWidth()/2)-3, 1));
        drawText("DUARTE  LOPES",color,new TerminalPosition((screen.getWidth()/2)-6, 8));
        drawText("LEANDRO SILVA",color,new TerminalPosition((screen.getWidth()/2)-6, 15));
        drawText("TIAGO BARBOSA",color,new TerminalPosition((screen.getWidth()/2)-6, 22));
        drawText("Press any key to exit",color,new TerminalPosition(screen.getWidth()-21,screen.getHeight()));
    }

    public void checkInput(Game game) throws IOException {
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()!= KeyType.EOF) {
                returnMenu(game);
            }
        }
    }
}