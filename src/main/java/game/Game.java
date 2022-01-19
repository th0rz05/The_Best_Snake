package game;

import state.State;

import java.io.IOException;

public class Game {
    State gamestate;
    int fps;

    public Game(State gamestate, int fps) {
        this.gamestate = gamestate;
        this.fps = fps;
    }

    public State getGamestate() {
        return gamestate;
    }

    public void setGameState(State state){
        this.gamestate = state;
    }

    public void start(){
        int frametime = 1000/fps;

        while(gamestate!= null){
            long startTime = System.currentTimeMillis();

            try{
                gamestate.step(this);
            }
            catch(IOException e){
                e.printStackTrace();
            }


            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frametime - elapsedTime;

            if(sleepTime>0) try{
                Thread.sleep(sleepTime);
            }catch (InterruptedException e){

            }

        }
    }
}
