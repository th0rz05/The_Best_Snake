package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import gui.LanternaGUI;
import observer.KeyboardObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class EndLevelXState extends State {
    KeyboardObserver observer;
    String name;
    double time;
    String backgroundColor;
    String filename;

    public EndLevelXState(LanternaGUI screen, double time) {
        super(screen);
        observer = new KeyboardObserver(screen);
        name = "";
        this.time = time;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground(backgroundColor);
        drawText("You Won! :)", "#FF0000", new TerminalPosition(20,3));
        drawText("Please Enter your name", "#000000", new TerminalPosition(15,9));
        drawText("Time: " + time + " s", "#000000", new TerminalPosition(30,25));
        checkInput(game);
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

    public void checkInput(Game game) throws IOException{
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()== KeyType.Character && name.length() <= 10 ){
                name += key.getCharacter().toString();
            }
            else if(key.getKeyType()== KeyType.Enter){
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                saveScore();
                changeState(game,new MenuState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
            }
            else if(key.getKeyType()== KeyType.Backspace && name.length() >=1){
                name = name.substring(0,name.length()-1);
            }
        }

    }

    public void saveScore() throws IOException {
        File scoreboard = new File(filename);   // Melhorar método!
        BufferedWriter scoreWriter = new BufferedWriter(new FileWriter(scoreboard,true));
        BufferedReader scoreReader = new BufferedReader(new FileReader(scoreboard));
        List<String> scoreboardLs = new ArrayList<>();
        String s;

        if(scoreboard.length() == 0) {
            scoreWriter.write(name + " " + time);
            scoreWriter.close();
            scoreReader.close();
            return;
        }

        while((s = scoreReader.readLine()) != null)
            scoreboardLs.add(s);
        scoreWriter.close();

        boolean added = false;
        int size =scoreboardLs.size();
        for(int i = 0; i < size; i++){
            String[] aux = scoreboardLs.get(i).split(" ");
            if(Double.parseDouble(aux[1]) > time ){
                scoreboardLs.add(i,name + " " + time);
                added = true;
                break;
            }
        }
        if(!added)
            scoreboardLs.add(name + " " + time);

        BufferedWriter scoreWriter2 = new BufferedWriter(new FileWriter(scoreboard));
        for(int i = 0; i < scoreboardLs.size(); i++)
            scoreWriter2.write(scoreboardLs.get(i) + "\n");

        scoreWriter2.close();
        scoreReader.close();
    }
}


