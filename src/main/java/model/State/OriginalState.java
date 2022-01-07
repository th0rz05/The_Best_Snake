package model.State;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import model.Arena;
import model.Position;
import model.element.Snake;
import org.ietf.jgss.GSSName;
import view.Game;
import view.LanternaGUI;

import java.io.IOException;

public class OriginalState extends State {
    Arena arena;
    Snake snake_tobias;

    public OriginalState(LanternaGUI screen) {
        super(screen);
        snake_tobias = new Snake(new Position(30,15));
        arena = new Arena(snake_tobias,screen.getHeight(),screen.getWidth());
    }


    @Override
    public void step(Game game){
        try{
            screen.getScreen().startScreen();
            screen.getScreen().clear();
            arena.draw(screen.getGraphics());
            arena.execute();
            screen.getScreen().refresh();
            System.out.println("On Original!");
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void drawText(String text,String color,TerminalPosition position){
        screen.getGraphics().setForegroundColor(TextColor.Factory.fromString(color));
        screen.getGraphics().putString(position, text);
    }
}
