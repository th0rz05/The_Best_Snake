package pt.up.ldts.general;

import pt.up.ldts.elements.Snake;
import pt.up.ldts.elements.Wall;
import pt.up.ldts.elements.fruit.*;
import pt.up.ldts.gui.LanternaGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ArenaBuilder {

    Arena arena;

    public ArenaBuilder(List<Snake> two_snakes, LanternaGUI screen) {
        arena = new Arena();
        arena.height = screen.getHeight()-1;
        arena.width = screen.getWidth()-1;
        for(Snake snake : two_snakes){
            arena.getElements().add(snake);
            arena.getSnakes().add(snake);
        }
        arena.getPossibleFruits().add(new Apple(new Position(0,0)));
        arena.getPossibleFruits().add(new Orange(new Position(0,0)));
        arena.getPossibleFruits().add(new Kiwi(new Position(0,0)));
        arena.getPossibleFruits().add(new Banana(new Position(0,0)));
        arena.getPossibleFruits().add(new Peach(new Position(0,0)));
        arena.getPossibleFruits().add(new Grape(new Position(0,0)));
        arena.getPossibleFruits().add(new Cherry(new Position(0,0)));
        arena.getPossibleFruits().add(new Mistery(new Position(0,0)));
    }

    public void buildWalls(String File_name){
        buildGeneralWalls();
        try {
            File myObj = new File(File_name);
            Scanner myReader = new Scanner(myObj,UTF_8.name());
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] position = data.split(" ");
                int x = Integer.parseInt(position[0]);
                int y = Integer.parseInt(position[1]);
                arena.getWalls().add(new Wall(new Position(x,y)));
                arena.getElements().add(new Wall(new Position(x,y)));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void buildGeneralWalls(){
        for(int i = 0;i<= arena.width;i++){
            arena.getWalls().add(new Wall(new Position(i,0)));
            arena.getElements().add(new Wall(new Position(i,0)));
            arena.getWalls().add(new Wall(new Position(i,arena.height)));
            arena.getElements().add(new Wall(new Position(i, arena.height)));
        }
        for(int i = 1;i< arena.height;i++){
            arena.getWalls().add(new Wall(new Position(0,i)));
            arena.getElements().add(new Wall(new Position(0,i)));
            arena.getWalls().add(new Wall(new Position(arena.width,i)));
            arena.getElements().add(new Wall(new Position(arena.width,i)));
        }
    }

    public void buildDoor(Position position){
        arena.door.setPosition(position);
    }

    public Arena getArena() {
        return arena;
    }
}
