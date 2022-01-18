package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import game.Game;
import gui.LanternaGUI;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class EndLevelXState extends State {
    double time;
    String backgroundColor;
    String filename;

    public EndLevelXState(LanternaGUI screen, double time) {
        super(screen);
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
        drawAllText("#000000");
        checkInputEndGame(game);
        screen.getScreen().refresh();
    }

    public void drawAllText(String color){
        drawText("You Won! :)", "#FF0000", new TerminalPosition(20,3));
        drawText("Please Enter your name", color, new TerminalPosition(15,9));
        drawText("Time: " + time + " s", color, new TerminalPosition(30,25));
        drawText("Your Name: " + name,color, new TerminalPosition(12,16));
    }


    public void saveScore() throws IOException {
        File scoreboard = new File(filename);
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


