package it.unibo.squaresgameteam.squares.controller.interfaces;

import java.io.IOException;

public interface Setting {
    
    void setColorPlayer1();
    
    void setColorPlayer2();
    
    void turnVolumeOn();
    
    void turnVolumeOff();
    
    void createAudio() throws IOException;

}
