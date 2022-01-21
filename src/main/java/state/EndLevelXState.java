package state;

import com.googlecode.lanterna.TerminalPosition;
import general.Game;
import gui.LanternaGUI;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

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

    @Override
    public void drawAllText(String color){
        drawText("YOU WON! :)", "#FF0000", new TerminalPosition(20,3));
        drawText("PLEASE ENTER YOUR NAME", color, new TerminalPosition(15,9));
        drawText("TIME: " + time + " S", color, new TerminalPosition(30,25));
        drawText("YOUR NAME: " + name,color, new TerminalPosition(12,16));
    }

    @Override
    public void saveScore() throws IOException {
        File scoreboard = new File(filename);
        BufferedWriter scoreWriter = Files.newBufferedWriter(scoreboard.toPath(), UTF_8, CREATE, APPEND);
        BufferedReader scoreReader = Files.newBufferedReader(scoreboard.toPath(), UTF_8);
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

        BufferedWriter scoreWriter2 = Files.newBufferedWriter(scoreboard.toPath(), UTF_8);
        for(int i = 0; i < scoreboardLs.size(); i++)
            scoreWriter2.write(scoreboardLs.get(i) + "\n");

        scoreWriter2.close();
        scoreReader.close();
    }
}


