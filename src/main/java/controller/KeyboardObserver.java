package controller;

import com.googlecode.lanterna.input.KeyStroke;
import view.LanternaGUI;

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

    public boolean readinput(){
        try{
            keys.clear();
            KeyStroke key = screen.getScreen().pollInput();
            if(key!=null){
                keys.add(key);
                while(key != null){
                    key = screen.getScreen().pollInput();
                }
                return true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
