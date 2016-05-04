package model.interfaces;

public interface PlayedTime {

    void gameStarted(Turn currentGame);
    
    void gameEnded(Turn currentGame);
    
    Double getElapsedTime();
    
    void setElapsedTime(Double totalPlayTime);
}
