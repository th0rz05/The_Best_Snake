package state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import elements.button.BigButton;
import elements.button.Button;
import elements.button.SmallButton;
import observer.KeyboardObserver;
import game.Position;
import game.Game;
import gui.LanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuState extends State {

    KeyboardObserver observer;
    List<Button> buttonList = new ArrayList<>();
    Button actualbutton;

    public MenuState(LanternaGUI screen) {
        super(screen);
        observer = new KeyboardObserver(screen);
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 6),"  ORIGINAL   "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 11)," MULTIPLAYER "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 16),"  CHALLENGE  "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 21)," SCOREBOARD  "));
        buttonList.add(new BigButton(new Position((screen.getWidth()/2)-7, 26),"    RULES    "));
        buttonList.add(new SmallButton(new Position(3, 26),"CREDITS"));
        actualbutton=buttonList.get(0);
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#31B2D8");
        drawAllText("#000097");
        drawButtons();
        screen.getScreen().refresh();
        checkInput(game);
    }


    public void drawAllText(String color){
        drawText("THE BEST SNAKE",color,new TerminalPosition((screen.getWidth()/2)-7, 1));
        drawText("Q to exit",color,new TerminalPosition(screen.getWidth()-9, screen.getHeight()));
    }

    public void drawButtons(){
        for(Button b : buttonList){
            if (b.equals(actualbutton)){
                b.setHighlight(true);
            }
            b.draw(screen.getGraphics());
            b.setHighlight(false);
        }
    }

    public void checkInput(Game game) throws IOException{
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()== KeyType.Enter){
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                enterState(game);
            }
            if(key.getKeyType()== KeyType.Character && key.getCharacter().toString().equalsIgnoreCase("q")){
                screen.getScreen().stopScreen();
                screen.getScreen().close();
                changeState(game,null);
            }
            if(key.getKeyType()== KeyType.ArrowDown){
                int index = (buttonList.indexOf(actualbutton)+1);
                if(index==buttonList.size()){
                    index = 0;
                }
                actualbutton = buttonList.get(index);
            }
            if(key.getKeyType()== KeyType.ArrowUp){
                int index = (buttonList.indexOf(actualbutton)-1) ;
                if(index==-1){
                    index = buttonList.size()-1;
                }
                actualbutton = buttonList.get(index);
            }
        }
    }

    public void enterState(Game game){
        switch(actualbutton.getText()){
            case "  ORIGINAL   ": changeState(game,new OriginalState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
            case " MULTIPLAYER ": changeState(game,new MultiplayerState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
            case "  CHALLENGE  ": changeState(game,new ChallengeState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
            case " SCOREBOARD  ": changeState(game,new ScoreboardState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
            case "    RULES    ": changeState(game,new RulesState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
            case "CREDITS": changeState(game,new CreditsState(new LanternaGUI(screen.getHeight(), screen.getWidth())));break;
        }
    }
}

