package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import observer.KeyboardObserver;
import game.Game;
import gui.LanternaGUI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        drawText(" GAME OVER ", "#FF0000", new TerminalPosition(20,3));
        drawText("Please Enter your name", "#000000", new TerminalPosition(15,9));
        drawText("Score: " + score, "#000000", new TerminalPosition(12,25));
        drawText("Time: " + time + " s", "#000000", new TerminalPosition(30,25));
        checkInput(game);
        drawText("Your Name: " + name,"#000000", new TerminalPosition(12,16));
        screen.getScreen().refresh();
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
