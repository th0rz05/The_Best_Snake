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

public class EndMultiplayerState extends State{
    KeyboardObserver observer;
    String name1;
    String name2;
    int score1;
    int score2;
    double time;
    boolean firstNameDone;

    public EndMultiplayerState(LanternaGUI screen, int score1, int score2, double time) {
        super(screen);
        observer = new KeyboardObserver(screen);
        name1 = "";
        name2 = "";
        this.score1 = score1;
        this.score2 = score2;
        this.time = time;
        this.firstNameDone = false;
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        if(!firstNameDone){
            drawBackground("#2DF168");
            drawText("You Won! :)", "#000000", new TerminalPosition(20,3));
            drawText("Please Enter your name", "#000000", new TerminalPosition(15,9));
            drawText("Score: " + score1, "#000000", new TerminalPosition(12,25));
            checkInput(game);
            drawText("Your Name: " + name1,"#000000", new TerminalPosition(12,16));
        }
        else{
            drawBackground("#F94555");
            drawText("You Lost! :(", "#000000", new TerminalPosition(20,3));
            drawText("Please Enter your name", "#000000", new TerminalPosition(15,9));
            drawText("Score: " + score2, "#000000", new TerminalPosition(12,25));
            checkInput(game);
            drawText("Your Name: " + name2,"#000000", new TerminalPosition(12,16));
        }
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
            if(!firstNameDone){
                if(key.getKeyType()== KeyType.Character && name1.length() <= 15 ){
                    name1 += key.getCharacter().toString();
                }
                else if(key.getKeyType()== KeyType.Enter){
                    firstNameDone = true;
                }
                else if(key.getKeyType()== KeyType.Backspace && name1.length() >=1){
                    name1 = name1.substring(0,name1.length()-1);
                }
            }
            else{
                if(key.getKeyType()== KeyType.Character && name2.length() <= 15 ){
                    name2 += key.getCharacter().toString();
                }
                else if(key.getKeyType()== KeyType.Enter){
                    screen.getScreen().stopScreen();
                    screen.getScreen().close();
                    saveScore();
                    changeState(game,new MenuState(new LanternaGUI(screen.getHeight(), screen.getWidth())));
                }
                else if(key.getKeyType()== KeyType.Backspace && name2.length() >=1){
                    name2 = name2.substring(0,name2.length()-1);
                }
            }
        }
    }

    public void saveScore() throws IOException {
        File scoreboard = new File("src/main/resources/Scoreboards/MultiplayerScoreBoard.txt");
        BufferedWriter scoreWriter = new BufferedWriter(new FileWriter(scoreboard,true));
        BufferedReader scoreReader = new BufferedReader(new FileReader(scoreboard));
        List<String> scoreboardLs = new ArrayList<>();
        String s;

        if(scoreboard.length() == 0) {
            scoreWriter.write(name1 + " " + score1 + " " + name2 + " " + score2 + " " + time);
            scoreWriter.close();
            scoreReader.close();
            return;
        }

        scoreboardLs.add(name1 + " " + score1 + " " + name2 + " " + score2 + " " + time);

        while((s = scoreReader.readLine()) != null)
            scoreboardLs.add(s);
        scoreWriter.close();


        BufferedWriter scoreWriter2 = new BufferedWriter(new FileWriter(scoreboard));
        for(int i = 0; i < scoreboardLs.size(); i++)
            scoreWriter2.write(scoreboardLs.get(i) + "\n");

        scoreWriter2.close();
        scoreReader.close();
    }

}