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
    List<KeyType> arrows = new ArrayList<>();
    List<String> wasd = new ArrayList<>();


    public List<KeyStroke> getKeys() {
        return keys;
    }

    public void setKeys(List<KeyStroke> keys) {
        this.keys = keys;
    }

    public KeyboardObserver(LanternaGUI screen) {
        this.screen = screen;
        arrows.add(KeyType.ArrowDown);
        arrows.add(KeyType.ArrowUp);
        arrows.add(KeyType.ArrowRight);
        arrows.add(KeyType.ArrowLeft);
        wasd.add("w");
        wasd.add("a");
        wasd.add("s");
        wasd.add("d");
    }

    public boolean readinput(){
        try{
            keys.clear();
            KeyStroke key = screen.getScreen().pollInput();
            if(key != null) {
                if (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().toLowerCase() == ("q") || key.getCharacter().toString().toLowerCase() == ("p"))) {
                    keys.add(key);
                    while (key != null) {
                        key = screen.getScreen().pollInput();
                    }
                    return true;
                } else if (key.getKeyType() == KeyType.ArrowUp || key.getKeyType() == KeyType.ArrowDown || key.getKeyType() == KeyType.ArrowLeft || key.getKeyType() == KeyType.ArrowRight) {
                    keys.add(key);
                    while (key != null && (key.getKeyType() == KeyType.ArrowUp || key.getKeyType() == KeyType.ArrowDown || key.getKeyType() == KeyType.ArrowLeft || key.getKeyType() == KeyType.ArrowRight)) {
                        key = screen.getScreen().pollInput();
                    }
                    if (key == null) {
                        return true;
                    }
                    if (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().toLowerCase() == ("a") || key.getCharacter().toString().toLowerCase() == ("w") || key.getCharacter().toString().toLowerCase() == ("s") || key.getCharacter().toString().toLowerCase() == ("d"))) {
                        keys.add(key);
                        while (key != null) {
                            key = screen.getScreen().pollInput();
                        }
                    }
                    if (key == null) {
                        return true;
                    }
                    if (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().toLowerCase() == ("q") || key.getCharacter().toString().toLowerCase() == ("p"))) {
                        keys.add(key);
                        while (key != null) {
                            key = screen.getScreen().pollInput();
                        }
                        return true;
                    }
                } else if (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().toLowerCase() == ("a") || key.getCharacter().toString().toLowerCase() == ("w") || key.getCharacter().toString().toLowerCase() == ("s") || key.getCharacter().toString().toLowerCase() == ("d"))) {
                    keys.add(key);
                    while (key != null && (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().toLowerCase() == ("a") || key.getCharacter().toString().toLowerCase() == ("w") || key.getCharacter().toString().toLowerCase() == ("s") || key.getCharacter().toString().toLowerCase() == ("d")))) {
                        key = screen.getScreen().pollInput();
                    }
                    if (key == null) {
                        return true;
                    }
                    if (key.getKeyType() == KeyType.ArrowUp || key.getKeyType() == KeyType.ArrowDown || key.getKeyType() == KeyType.ArrowLeft || key.getKeyType() == KeyType.ArrowRight) {
                        keys.add(key);
                        while (key != null) {
                            key = screen.getScreen().pollInput();
                        }
                    }
                    if (key == null) {
                        return true;
                    }
                    if (key.getKeyType() == KeyType.Character && (key.getCharacter().toString().toLowerCase() == ("q") || key.getCharacter().toString().toLowerCase() == ("p"))) {
                        keys.add(key);
                        while (key != null) {
                            key = screen.getScreen().pollInput();
                        }
                        return true;
                    }
                } else {
                    keys.add(key);
                    while (key != null) {
                        key = screen.getScreen().pollInput();
                    }
                    return true;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
