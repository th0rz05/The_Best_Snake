package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import general.Game;
import gui.LanternaGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RulesState extends State{

    String filepath;
    int page, initialRow;

    public RulesState(LanternaGUI screen) {
        super(screen);
        filepath = "src/main/resources/Rules/rules1.txt";
        page = 1;
        initialRow = 4;
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#69B8F9");
        drawAllText("#000097");
        drawFromFile(filepath);
        screen.getScreen().refresh();
        checkInput(game);
    }

    @Override
    public void drawAllText(String color){
        drawText("RULES",color,new TerminalPosition((screen.getWidth()/2)-2, 1));
        if(page!=3){
            drawText("--next page--",color,new TerminalPosition(screen.getWidth()-13,screen.getHeight()));
        }
        else{
            drawText("press any key to exit", color, new TerminalPosition(screen.getWidth() - 21, screen.getHeight()));
        }
    }

    public void drawFromFile(String file) {
        int i = initialRow;
        try {
            Scanner scanner = new Scanner(new File(file), UTF_8.name());
            while (scanner.hasNextLine()) {
                drawText(scanner.nextLine(), "#000000", new TerminalPosition(2, i));
                i += 1;
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkInput(Game game) throws IOException{
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()!= KeyType.EOF) {
                if(page==1) {
                    filepath = "src/main/resources/Rules/rules2.txt";
                    page = 2;
                    initialRow = 5;
                }
                else if(page==2){
                    filepath = "src/main/resources/Rules/rules3.txt";
                    page = 3;
                    initialRow = 5;
                }
                else if(page==3){
                    returnMenu(game);
                }
            }
        }
    }
}
