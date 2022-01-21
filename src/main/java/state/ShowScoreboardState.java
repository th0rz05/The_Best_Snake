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

public class ShowScoreboardState extends State{

    String filename;

    public ShowScoreboardState(LanternaGUI screen,String filename) {
        super(screen);
        this.filename = filename;
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#B3B1E1");
        drawAllText("#000097");
        drawFromFile(filename);
        screen.getScreen().refresh();
        checkInput(game);
    }

    @Override
    public void drawAllText(String color){
        drawText("SCOREBOARD",color,new TerminalPosition((screen.getWidth()/2)-5, 1));
        drawText("press any key to exit",color,new TerminalPosition(screen.getWidth()-21,screen.getHeight()));
    }

    public void drawFromFile(String file) throws FileNotFoundException{
        int i = 7;
        Scanner scanner = new Scanner(new File(file),UTF_8.name());
        while (scanner.hasNextLine() && i < 28) {
            String[] aux = scanner.nextLine().split(" ");
            if(aux.length == 2){
                drawTextAux2(aux,i);
            }
            else if(aux.length == 3){
                drawTextAux3(aux,i);
            }
            else if(aux.length == 5){
                drawTextAux5(aux,i);
            }
            i += 2;
        }
        scanner.close();
    }

    public void checkInput(Game game) throws IOException{
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()!= KeyType.EOF) {
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                changeState(game, new ScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
            }
        }
    }

    public void drawTextAux2(String[] aux,int i){
        drawText("NAME", "#FF0000", new TerminalPosition(12, 4));
        drawText("TIME", "#FF0000", new TerminalPosition(30, 4));
        drawText(aux[0], "#FFFFFF", new TerminalPosition(12, i));
        drawText(aux[1] + " S", "#FFFF00", new TerminalPosition(30, i));
    }

    public void drawTextAux3(String[] aux,int i){
        drawText("NAME", "#FF0000", new TerminalPosition(8, 4));
        drawText("SCORE", "#FF0000", new TerminalPosition(26, 4));
        drawText("TIME", "#FF0000", new TerminalPosition(38, 4));
        drawText(aux[0], "#FFFFFF", new TerminalPosition(8, i));
        drawText(aux[1], "#00FF00", new TerminalPosition(26, i));
        drawText(aux[2] + " S", "#FFFF00", new TerminalPosition(38, i));
    }

    public void drawTextAux5(String[] aux,int i){
        drawText("WINNER", "#00FF00", new TerminalPosition(2, 4));
        drawText("SCORE", "#00FF00", new TerminalPosition(14, 4));
        drawText("LOSER", "#FF0000", new TerminalPosition(20, 4));
        drawText("SCORE", "#FF0000", new TerminalPosition(32, 4));
        drawText("TIME", "#FFFF00", new TerminalPosition(40, 4));
        drawText(aux[0], "#FFFFFF", new TerminalPosition(2, i));
        drawText(aux[1], "#FFFFFF", new TerminalPosition(14, i));
        drawText(aux[2], "#FFFFFF", new TerminalPosition(20, i));
        drawText(aux[3], "#FFFFFF", new TerminalPosition(32, i));
        drawText(aux[4] + " S", "#FFFFFF", new TerminalPosition(40, i));
    }
}
