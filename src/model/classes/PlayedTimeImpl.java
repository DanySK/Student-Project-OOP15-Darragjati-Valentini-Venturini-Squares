package model.classes;

import model.interfaces.PlayedTime;

public class PlayedTimeImpl implements PlayedTime {

    Integer totalPlayTime = 0;
    
    @Override
    public void gameStarted() {
        System.currentTimeMillis();
    }

    @Override
    public void gameEnded() {
        System.currentTimeMillis();
        
    }

    @Override
    public Integer getElapsedTime() {

        return this.totalPlayTime;
    }

    public void setElapsedTime(){
        
    }
}
