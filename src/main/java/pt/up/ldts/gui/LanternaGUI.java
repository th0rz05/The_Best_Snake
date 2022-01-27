package pt.up.ldts.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LanternaGUI {
    TerminalScreen screen;
    TextGraphics graphics;
    int height;
    int width;
    String filename;

    public LanternaGUI(int h, int w){
        this.height = h;
        this.width = w;
        try{
            TerminalSize terminalSize = new TerminalSize(width, height +1 );
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminalFactory.setForceAWTOverSwing(true);
            terminalFactory.setTerminalEmulatorFontConfiguration(loadFont("src/main/resources/fonts/square.ttf"));
            terminalFactory.setTerminalEmulatorTitle("THE BEST SNAKE");
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.graphics = screen.newTextGraphics();
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        }
        catch (IOException  | FontFormatException e){
            e.printStackTrace();
        }
    }

    public LanternaGUI(int h, int w , String filename){
        this.height = h;
        this.width = w;
        try{
            TerminalSize terminalSize = new TerminalSize(width, height +1 );
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminalFactory.setForceAWTOverSwing(true);
            terminalFactory.setTerminalEmulatorFontConfiguration(loadFont(filename));
            terminalFactory.setTerminalEmulatorTitle("THE BEST SNAKE");
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.graphics = screen.newTextGraphics();
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        }
        catch (IOException  | FontFormatException e){
            e.printStackTrace();
        }
    }

    public TerminalScreen getScreen() {
        return screen;
    }

    public void setScreen(TerminalScreen screen) {
        this.screen = screen;
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public AWTTerminalFontConfiguration loadFont(String filename) throws FontFormatException, IOException{
        File fontFile = new File(filename);
        Font font = Font.createFont(Font.TRUETYPE_FONT,fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.BOLD, 23);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);

    }
}
