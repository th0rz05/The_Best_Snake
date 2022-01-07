package model.State;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import view.Game;
import view.LanternaGUI;

import java.io.IOException;

public class MenuState extends State {

    int x =0;
    public MenuState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(Game game) {
        try{
            screen.getScreen().startScreen();
            screen.getScreen().clear();
            drawText("THE BEST SNAKE","#FFFFFF",new TerminalPosition(23,1));
            drawText("ORIGINAL","#FFFFFF",new TerminalPosition(26,6));
            screen.getScreen().refresh();
            System.out.println("On Menu!");
            x++;
            if(x==10){
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                changeState(game,new OriginalState(new LanternaGUI(30,60)));
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

