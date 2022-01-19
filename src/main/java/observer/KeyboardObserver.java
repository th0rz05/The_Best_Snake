package observer;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import gui.LanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KeyboardObserver {
    LanternaGUI screen;
    List<KeyStroke> keys = new ArrayList();

    public List<KeyStroke> getKeys() {
        return keys;
    }

    public KeyboardObserver(LanternaGUI screen) {
        this.screen = screen;
    }

    public boolean readinput() throws IOException{
        keys.clear();
        KeyStroke key = screen.getScreen().pollInput();
        if(key != null) {
            if (isPauseorQuit(key)) {
                return quitPause(key);
            } else if (isArrow(key)) {
                return arrows(key);
            } else if (isWasd(key)) {
                return wasd(key);
            } else {
                return quitPause(key);
            }
        }
        return false;
    }

    public boolean quitPause(KeyStroke key) throws IOException{
        deleteBuffer(key);
        return true;
    }

    public boolean arrows(KeyStroke key) throws IOException{
        keys.add(key);
        while (isArrow(key)) {
            key = screen.getScreen().pollInput();
        }
        if (isWasd(key)) {
            deleteBuffer(key);
        }
        if (isPauseorQuit(key)) {
            deleteBuffer(key);
            return true;
        }
        return  true;
    }

    public boolean wasd(KeyStroke key) throws IOException{
        keys.add(key);
        while (isWasd(key)) {
            key = screen.getScreen().pollInput();
        }
        if (isArrow(key)) {
            deleteBuffer(key);
        }
        if (isPauseorQuit(key)) {
            deleteBuffer(key);
            return true;
        }
        return true;
    }

    public void deleteBuffer(KeyStroke key) throws IOException{
        keys.add(key);
        while (key != null) {
            key = screen.getScreen().pollInput();
        }
    }

    public boolean isArrow(KeyStroke key){
        return key != null && (key.getKeyType() == KeyType.ArrowUp || key.getKeyType() == KeyType.ArrowDown || key.getKeyType() == KeyType.ArrowLeft || key.getKeyType() == KeyType.ArrowRight);
    }

    public boolean isWasd(KeyStroke key){
        return key != null && (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().equalsIgnoreCase("a") || key.getCharacter().toString().equalsIgnoreCase("w") || key.getCharacter().toString().equalsIgnoreCase("s") || key.getCharacter().toString().equalsIgnoreCase("d")));
    }

    public boolean isPauseorQuit(KeyStroke key){
        return key!=null && key.getKeyType() == KeyType.Character && (key.getCharacter().toString().equalsIgnoreCase("q") || key.getCharacter().toString().equalsIgnoreCase("p"));
    }

}
