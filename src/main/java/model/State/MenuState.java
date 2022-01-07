package model.State;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import view.Game;
import view.LanternaGUI;

public class MenuState extends State {

    public MenuState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(Game game) {

        TextGraphics graphics = screen.getGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.putString(new TerminalPosition(5, 5), "Original");



        System.out.println("On Menu!");
    }
}

