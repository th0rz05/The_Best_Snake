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

    public void setKeys(List<KeyStroke> keys) {
        this.keys = keys;
    }

    public KeyboardObserver(LanternaGUI screen) {
        this.screen = screen;
    }

    public boolean readinput() throws IOException{
        keys.clear();
        KeyStroke key = screen.getScreen().pollInput();
        if(key != null) {
            if (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().equalsIgnoreCase("q") || key.getCharacter().toString().equalsIgnoreCase("p"))) {
                return quitPause(key);
            } else if (key.getKeyType() == KeyType.ArrowUp || key.getKeyType() == KeyType.ArrowDown || key.getKeyType() == KeyType.ArrowLeft || key.getKeyType() == KeyType.ArrowRight) {
                return arrows(key);
            } else if (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().equalsIgnoreCase("a") || key.getCharacter().toString().equalsIgnoreCase("w") || key.getCharacter().toString().equalsIgnoreCase("s") || key.getCharacter().toString().equalsIgnoreCase("d"))) {
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
        while (key != null && (key.getKeyType() == KeyType.ArrowUp || key.getKeyType() == KeyType.ArrowDown || key.getKeyType() == KeyType.ArrowLeft || key.getKeyType() == KeyType.ArrowRight)) {
            key = screen.getScreen().pollInput();
        }
        if(keyNull(key)){
            return true;
        }
        if (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().equalsIgnoreCase("a") || key.getCharacter().toString().equalsIgnoreCase("w") || key.getCharacter().toString().equalsIgnoreCase("s") || key.getCharacter().toString().equalsIgnoreCase("d"))) {
            deleteBuffer(key);
        }
        if(keyNull(key)){
            return true;
        }
        if (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().equalsIgnoreCase("q") || key.getCharacter().toString().equalsIgnoreCase("p"))) {
            deleteBuffer(key);
            return true;
        }
        return  true;
    }

    public boolean wasd(KeyStroke key) throws IOException{
        keys.add(key);
        while (key != null && (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().equalsIgnoreCase("a") || key.getCharacter().toString().equalsIgnoreCase("w") || key.getCharacter().toString().equalsIgnoreCase("s") || key.getCharacter().toString().equalsIgnoreCase("d")))) {
            key = screen.getScreen().pollInput();
        }
        if(keyNull(key)){
            return true;
        }
        if (key.getKeyType() == KeyType.ArrowUp || key.getKeyType() == KeyType.ArrowDown || key.getKeyType() == KeyType.ArrowLeft || key.getKeyType() == KeyType.ArrowRight) {
            deleteBuffer(key);
        }
        keyNull(key);
        if (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().equalsIgnoreCase("q") || key.getCharacter().toString().equalsIgnoreCase("p"))) {
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

    public boolean keyNull(KeyStroke key){
        return key==null;
    }
}
