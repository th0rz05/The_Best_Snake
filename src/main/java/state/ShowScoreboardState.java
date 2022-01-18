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

public class ShowScoreboardState extends State{

    KeyboardObserver observer;
    String filename;

    public ShowScoreboardState(LanternaGUI screen,String filename) {
        super(screen);
        observer = new KeyboardObserver(screen);
        this.filename = filename;
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#31B2D8");
        drawAllText("#000097");
        drawFromFile(filename);
        screen.getScreen().refresh();
        checkInput(game);
    }

    public void drawAllText(String color){
        drawText("SCOREBOARD",color,new TerminalPosition((screen.getWidth()/2)-5, 1));
        drawText("Press any key to exit",color,new TerminalPosition(screen.getWidth()-21,screen.getHeight()));
    }

    public void drawFromFile(String file) {
        int i = 7;
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine() && i < 28) {
                String[] aux = scanner.nextLine().split(" ");
                if(aux.length == 2){
                    drawText("Name", "#FF0000", new TerminalPosition(12, 4));
                    drawText("Time", "#FF0000", new TerminalPosition(30, 4));
                    drawText(aux[0], "#FFFFFF", new TerminalPosition(12, i));
                    drawText(aux[1] + " s", "#FFFF00", new TerminalPosition(30, i));
                }
                else if(aux.length == 3){
                    drawText("Name", "#FF0000", new TerminalPosition(8, 4));
                    drawText("Score", "#FF0000", new TerminalPosition(26, 4));
                    drawText("Time", "#FF0000", new TerminalPosition(38, 4));
                    drawText(aux[0], "#FFFFFF", new TerminalPosition(8, i));
                    drawText(aux[1], "#00FF00", new TerminalPosition(26, i));
                    drawText(aux[2] + " s", "#FFFF00", new TerminalPosition(38, i));
                }
                else if(aux.length == 5){
                    drawText("Winner", "#00FF00", new TerminalPosition(2, 4));
                    drawText("Score", "#00FF00", new TerminalPosition(14, 4));
                    drawText("Loser", "#FF0000", new TerminalPosition(20, 4));
                    drawText("Score", "#FF0000", new TerminalPosition(32, 4));
                    drawText("Time", "#FFFF00", new TerminalPosition(40, 4));
                    drawText(aux[0], "#FFFFFF", new TerminalPosition(2, i));
                    drawText(aux[1], "#FFFFFF", new TerminalPosition(14, i));
                    drawText(aux[2], "#FFFFFF", new TerminalPosition(20, i));
                    drawText(aux[3], "#FFFFFF", new TerminalPosition(32, i));
                    drawText(aux[4] + " s", "#FFFFFF", new TerminalPosition(40, i));
                }
                i += 2;
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
                    changeState(game, new ScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
