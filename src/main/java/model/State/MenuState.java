package model.State;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import controller.KeyboardObserver;
import view.Game;
import view.LanternaGUI;

import java.io.IOException;

public class MenuState extends State {

    KeyboardObserver observer;
    public MenuState(LanternaGUI screen) {
        super(screen);
        observer = new KeyboardObserver(screen);
    }

    @Override
    public void step(Game game) {
        try{
            screen.getScreen().startScreen();
            screen.getScreen().clear();
            drawText("THE BEST SNAKE","#FFFFFF",new TerminalPosition(23,1));
            drawText("ORIGINAL(PRESS 1)","#FFFFFF",new TerminalPosition(22,6));
            screen.getScreen().refresh();
            if(observer.readinput()){
                KeyStroke key = observer.getKeys().get(0);
                if(key.getKeyType()== KeyType.Character && key.equals(new KeyStroke('1',false,false,false))){
                    screen.getScreen().stopScreen();
                    screen.getScreen().close();
                    changeState(game,new OriginalState(new LanternaGUI(30,60)));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void drawText(String text,String color,TerminalPosition position){
        screen.getGraphics().setForegroundColor(TextColor.Factory.fromString(color));
        screen.getGraphics().putString(position, text);
    }
}

