package model.State;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import controller.KeyboardObserver;
import view.Game;
import view.LanternaGUI;

import java.io.*;
import java.util.Scanner;

public class EndOriginalState extends State{
    KeyboardObserver observer;
    String name;
    int score;
    double time;

    public EndOriginalState(LanternaGUI screen, int score, double time) {
        super(screen);
        observer = new KeyboardObserver(screen);
        name = "";
        this.score = score;
        this.time = time;
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#31B2D8");
        drawText("You Lost! :(", "#FF0000", new TerminalPosition(20,3));
        drawText("Please Enter your name", "#000000", new TerminalPosition(15,9));
        drawText("Score: " + String.valueOf(score), "#000000", new TerminalPosition(12,25));
        drawText("Time: " + String.valueOf(time) + " s", "#000000", new TerminalPosition(30,25));
        read_name(game);
        drawText("Your Name: " + name,"#000000", new TerminalPosition(12,16));
        screen.getScreen().refresh();
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

    public void read_name(Game game) throws IOException{
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()== KeyType.Character && name.length() <= 15 ){
                name += key.getCharacter().toString();
            }
            else if(key.getKeyType()== KeyType.Enter){
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                save_score();
                changeState(game,new MenuState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
            }
            else if(key.getKeyType()== KeyType.Backspace && name.length() >=1){
                name = name.substring(0,name.length()-1);
            }
        }

    }

    public void save_score() throws IOException {
        File scoreboard = new File("src/main/resources/Scoreboards/OriginalScoreBoard");
        PrintWriter scoreWriter = new PrintWriter(scoreboard);
        Scanner scoreReader = new Scanner(scoreboard);
        if(scoreboard.length() == 0){
            scoreWriter.println(name + " " + String.valueOf(score) + " " +String.valueOf(time));
            scoreWriter.close();
            scoreReader.close();
            return;
        }
        //Falta Inserir no sítio certo

/*        while (scoreReader.hasNextLine()) {
            String[] aux = scoreReader.nextLine().split(" ");
            scoreReader.nextLine();
            scoreboard.
            if(Integer.valueOf(aux[1]) > score){
                scoreWriter.println(name + " " + String.valueOf(score) + " " +String.valueOf(time));
            }
        }*/
        scoreWriter.close();
        scoreReader.close();
    }

}
