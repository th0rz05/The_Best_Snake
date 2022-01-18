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
    String name1;
    String name2;
    int score1;
    int score2;
    double time;
    boolean firstNameDone;
    boolean draw = false;

    public EndMultiplayerState(LanternaGUI screen, int score1, int score2, double time) {
        super(screen);
        name1 = "";
        name2 = "";
        this.score1 = score1;
        this.score2 = score2;
        this.time = time;
        this.firstNameDone = false;
        if(score1==-1){
            draw = true;
        }
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawAllText("#000000");
        checkInputEndGame(game);
        screen.getScreen().refresh();
    }

    public void drawAllText(String color){
        if(draw){
            drawBackground("#7D9BA8");
            drawText("DRAW! :/", color, new TerminalPosition(22,7));
            drawText("PRESS ANY KEY TO EXIT!",color, new TerminalPosition(15,16));
        }
        else if(!firstNameDone){
            drawBackground("#2DF168");
            drawText("You Won! :)", color, new TerminalPosition(20,3));
            drawText("Please Enter your name", color, new TerminalPosition(15,9));
            drawText("Score: " + score1, color, new TerminalPosition(12,25));
            drawText("Your Name: " + name1,color, new TerminalPosition(12,16));
        }
        else{
            drawBackground("#F94555");
            drawText("You Lost! :(", color, new TerminalPosition(20,3));
            drawText("Please Enter your name", color, new TerminalPosition(15,9));
            drawText("Score: " + score2, color, new TerminalPosition(12,25));
            drawText("Your Name: " + name2,color, new TerminalPosition(12,16));
        }
    }


    public void checkInputEndGame(Game game) throws IOException{
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(draw){
                if(key.getKeyType()!=KeyType.EOF){
                    returnMenu(game);
                }
            }
            else if(!firstNameDone){
                if(key.getKeyType()== KeyType.Character && name1.length() <= 10 ){
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
                if(key.getKeyType()== KeyType.Character && name2.length() <= 10 ){
                    name2 += key.getCharacter().toString();
                }
                else if(key.getKeyType()== KeyType.Enter){
                    saveScore();
                    returnMenu(game);
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