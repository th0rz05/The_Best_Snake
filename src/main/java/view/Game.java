package view;

import model.State.MenuState;
import model.State.State;

public class Game {
    State gamestate;
    int fps;

    public Game(State gamestate, int fps) {
        this.gamestate = gamestate;
        this.fps = fps;
    }

    public void start(){
        int frametime = 1000/fps;

        while(gamestate!= null){
            long startTime = System.currentTimeMillis();

            gamestate.step();

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frametime - elapsedTime;

            if(sleepTime>0) try{
                Thread.sleep(sleepTime);
            }catch (InterruptedException e){

            }

        }
    }
}
