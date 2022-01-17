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
        drawText("You Lost! :(", "#FF0000", new TerminalPosition(20,3));
        drawText("Please Enter your name", "#000000", new TerminalPosition(15,9));
        drawText("Score: " + score, "#000000", new TerminalPosition(12,25));
        drawText("Time: " + time + " s", "#000000", new TerminalPosition(30,25));
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
        File scoreboard = new File("src/main/resources/Scoreboards/OriginalScoreBoard");   // Melhorar método!
        BufferedWriter scoreWriter = new BufferedWriter(new FileWriter(scoreboard,true));
        BufferedReader scoreReader = new BufferedReader(new FileReader(scoreboard));
        List<String> scoreboard_ls = new ArrayList<>();
        String s;

        if(scoreboard.length() == 0) {
            scoreWriter.write(name + " " + String.valueOf(score) + " " + String.valueOf(time));
            scoreWriter.close();
            scoreReader.close();
            return;
        }

        while((s = scoreReader.readLine()) != null)
            scoreboard_ls.add(s);
        scoreWriter.close();

        boolean added = false;
        int size =scoreboard_ls.size();
        for(int i = 0; i < size; i++){
            String[] aux = scoreboard_ls.get(i).split(" ");
            if(Integer.parseInt(aux[1]) < score || (Integer.parseInt(aux[1]) == score  && Double.parseDouble(aux[2]) > time)){
                scoreboard_ls.add(i,name + " " + String.valueOf(score) + " " + String.valueOf(time));
                added = true;
                break;
            }
        }
        if(!added)
            scoreboard_ls.add(name + " " + String.valueOf(score) + " " +String.valueOf(time));

        BufferedWriter scoreWriter2 = new BufferedWriter(new FileWriter(scoreboard));
        for(int i = 0; i < scoreboard_ls.size(); i++)
            scoreWriter2.write(scoreboard_ls.get(i) + "\n");

        scoreWriter2.close();
        scoreReader.close();
    }

}
