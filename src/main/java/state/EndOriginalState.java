package state;

import com.googlecode.lanterna.TerminalPosition;
import general.Game;
import gui.LanternaGUI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EndOriginalState extends State{
    int score;
    double time;

    public EndOriginalState(LanternaGUI screen, int score, double time) {
        super(screen);
        name = "";
        this.score = score;
        this.time = time;
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#31B2D8");
        drawAllText("#000000");
        checkInputEndGame(game);
        screen.getScreen().refresh();
    }

    public void drawAllText(String color){
        drawText(" GAME OVER ", "#00FF00", new TerminalPosition(20,3));
        drawText("PLEASE ENTER YOUR NAME", color, new TerminalPosition(15,9));
        drawText("SCORE: " + score, color, new TerminalPosition(12,25));
        drawText("TIME: " + time + " S", color, new TerminalPosition(30,25));
        drawText("YOUR NAME: " + name,color, new TerminalPosition(12,16));
    }


    public void saveScore() throws IOException {
        File scoreboard = new File("src/main/resources/Scoreboards/OriginalScoreBoard.txt");   // Melhorar método!
        BufferedWriter scoreWriter = new BufferedWriter(new FileWriter(scoreboard,true));
        BufferedReader scoreReader = new BufferedReader(new FileReader(scoreboard));
        List<String> scoreboardLs = new ArrayList<>();
        String s;

        if(scoreboard.length() == 0) {
            scoreWriter.write(name + " " + score + " " + time);
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
            if(Integer.parseInt(aux[1]) < score || (Integer.parseInt(aux[1]) == score  && Double.parseDouble(aux[2]) > time)){
                scoreboardLs.add(i,name + " " + score + " " + time);
                added = true;
                break;
            }
        }
        if(!added)
            scoreboardLs.add(name + " " + score + " " + time);

        BufferedWriter scoreWriter2 = new BufferedWriter(new FileWriter(scoreboard));
        for(int i = 0; i < scoreboardLs.size(); i++)
            scoreWriter2.write(scoreboardLs.get(i) + "\n");

        scoreWriter2.close();
        scoreReader.close();
    }

}
