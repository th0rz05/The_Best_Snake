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

public class RulesState extends State{

    KeyboardObserver observer;

    public RulesState(LanternaGUI screen) {
        super(screen);
        observer = new KeyboardObserver(screen);
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#31B2D8");
        drawAllText("#000097");
        drawFromFile("src/main/resources/rules.txt");
        screen.getScreen().refresh();
        checkInput(game);
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

    public void drawAllText(String color){
        drawText("RULES",color,new TerminalPosition((screen.getWidth()/2)-2, 1));
        drawText("Press any key to exit",color,new TerminalPosition(screen.getWidth()-21,screen.getHeight()));
    }

    public void drawFromFile(String file) {
        int i = 6;
        // File f = new File(file);
        // boolean a = f.exists();
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                drawText(scanner.nextLine(), "#FFFFFF", new TerminalPosition(2, i));
                i += 1;
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkInput(Game game) {
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()!= KeyType.EOF) {
                try {
                    screen.getScreen().stopScreen();
                    screen.getScreen().close();
                    changeState(game, new MenuState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
