package pt.up.ldts.state;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import pt.up.ldts.general.Game;
import pt.up.ldts.gui.LanternaGUI;

import java.io.IOException;

public class CreditsState extends State {


    public CreditsState(LanternaGUI screen) {
        super(screen);
    }

    @Override
    public void step(Game game) throws IOException {
        screen.getScreen().clear();
        drawBackground("#69B8F9");
        drawAllText("#000097");
        screen.getScreen().refresh();
        checkInput(game);
    }

    @Override
    public void drawAllText(String color){
        drawText("CREDITS",color,new TerminalPosition((screen.getWidth()/2)-3, 1));
        drawText("DUARTE  LOPES",color,new TerminalPosition((screen.getWidth()/2)-6, 8));
        drawText("LEANDRO SILVA",color,new TerminalPosition((screen.getWidth()/2)-6, 15));
        drawText("TIAGO BARBOSA",color,new TerminalPosition((screen.getWidth()/2)-6, 22));
        drawText("press any key to exit",color,new TerminalPosition(screen.getWidth()-21,screen.getHeight()));
    }

    public void checkInput(Game game) throws IOException {
        if(observer.readinput()){
            KeyStroke key = observer.getKeys().get(0);
            if(key.getKeyType()!= KeyType.EOF) {
                returnMenu(game);
            }
        }
    }
}