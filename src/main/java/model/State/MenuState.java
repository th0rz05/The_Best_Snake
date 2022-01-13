package model.State;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import controller.Button;
import controller.KeyboardObserver;
import model.Position;
import view.Game;
import view.LanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuState extends State {

    KeyboardObserver observer;
    List<Button> buttonList = new ArrayList<>();

    public MenuState(LanternaGUI screen) {
        super(screen);
        observer = new KeyboardObserver(screen);
        buttonList.add(new Button(new Position((screen.getWidth()/2)-6, 6),"ORIGINAL"));
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#31B2D8");
        drawAllText("#000097");
        drawButtons();
        screen.getScreen().refresh();
        checkInput(game);
    }


    public void drawText(String text,String color,TerminalPosition position){
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

    public void drawAllText(String color){
        drawText("THE BEST SNAKE",color,new TerminalPosition((screen.getWidth()/2)-7, 1));
        //drawText("ORIGINAL",color,new TerminalPosition((screen.getWidth()/2)-4,6));
        drawText("Press Q to exit",color,new TerminalPosition(screen.getWidth()-15,screen.getHeight()));
    }

    public void drawButtons(){
        for(Button b : buttonList){
            b.draw(screen.getGraphics());
        }
    }

    public void checkInput(Game game) throws IOException{
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()== KeyType.Character && key.equals(new KeyStroke('1',false,false,false))){
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                changeState(game,new OriginalState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
            }
            if(key.getKeyType()== KeyType.Character && key.getCharacter().toString().equalsIgnoreCase("q")){
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                changeState(game,null);
            }
        }
    }
}

