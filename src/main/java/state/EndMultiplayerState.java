package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import elements.Snake;
import game.Game;
import game.Position;
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

    public EndMultiplayerState(LanternaGUI screen, Snake s1, Snake s2,double time) {
        super(screen);
        name1 = "";
        name2 = "";
        snake = s1;
        snake2 = s2;
        this.score1 = snake.getSize()-2;
        this.score2 = snake2.getSize()-2;
        this.time = time;
        this.firstNameDone = false;
    }

    public EndMultiplayerState(LanternaGUI screen) {
        super(screen);
        draw = true;
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
            drawBackground("#91F474");
            drawText("YOU WON! :)", color, new TerminalPosition(20,3));
            //drawSnake(snake);
            drawText("SNAKE1", snake.getBodyColor(), new TerminalPosition(23,5));
            drawText("PLEASE ENTER YOUR NAME", color, new TerminalPosition(15,9));
            drawText("SCORE: " + score1, color, new TerminalPosition(12,25));
            drawText("YOUR NAME: " + name1,color, new TerminalPosition(12,16));
        }
        else{
            drawBackground("#F59797");
            drawText("YOU LOST! :(", color, new TerminalPosition(20,3));
            drawText("SNAKE2", snake2.getBodyColor(), new TerminalPosition(23,5));
            //drawSnake(snake2);
            drawText("PLEASE ENTER YOUR NAME", color, new TerminalPosition(15,9));
            drawText("SCORE: " + score2, color, new TerminalPosition(12,25));
            drawText("YOUR NAME: " + name2,color, new TerminalPosition(12,16));
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
                readFirstName(key);
            }
            else{
                readSecondName(key,game);
            }
        }
    }

    public void readFirstName(KeyStroke key){
        if(key.getKeyType()== KeyType.Character && name1.length() <= 10 ){
            name1 += key.getCharacter().toString().toLowerCase();
        }
        else if(key.getKeyType()== KeyType.Enter){
            firstNameDone = true;
        }
        else if(key.getKeyType()== KeyType.Backspace && name1.length() >=1){
            name1 = name1.substring(0,name1.length()-1);
        }
    }

    public void readSecondName(KeyStroke key,Game game) throws IOException{
        if(key.getKeyType()== KeyType.Character && name2.length() <= 10 ){
            name2 += key.getCharacter().toString().toLowerCase();
        }
        else if(key.getKeyType()== KeyType.Enter){
            saveScore();
            returnMenu(game);
        }
        else if(key.getKeyType()== KeyType.Backspace && name2.length() >=1){
            name2 = name2.substring(0,name2.length()-1);
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

    public void drawSnake(Snake s){
        s.getSnakeHead().setPosition(new Position(27,5));
        s.getSnakeTail().setPosition(new Position(26,5));
        s.getSnakeHead().draw(screen.getGraphics());
        s.getSnakeTail().draw(screen.getGraphics());
        s.getSnakeTail().setPosition(new Position(25,5));
        s.getSnakeTail().draw(screen.getGraphics());
        s.getSnakeTail().setPosition(new Position(24,5));
        s.getSnakeTail().draw(screen.getGraphics());
        s.getSnakeTail().setPosition(new Position(23,5));
        s.getSnakeTail().draw(screen.getGraphics());
    }

}