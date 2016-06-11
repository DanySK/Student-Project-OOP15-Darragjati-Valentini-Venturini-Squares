package it.unibo.squaresgameteam.squares.controller.interfaces;
/**
 * 
 * @author Licia Valentini
 * 
 * Interface of the class MusicImpl
 *
 */
public interface Music {
    /**
     * this method starts the music.
     */
    void startMusic();
    
    /**
     * this method stops the music.
     */
    
    void stopMusic();
    
    /**
     * 
     * @return if the music is started.
     */

    boolean isStarted();

}
