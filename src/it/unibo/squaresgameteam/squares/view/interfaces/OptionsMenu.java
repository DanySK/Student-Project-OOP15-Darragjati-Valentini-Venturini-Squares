package it.unibo.squaresgameteam.squares.view.interfaces;

/**
 * This interface is used to customize the application's interface. The user can
 * lower or increase the application's volume and choose the colours of the
 * background and the players.
 */
public interface OptionsMenu {

    /**
     * sets on/off the application's music.
     */
    void setMusic();
    
    /**
     * resets the application's ranking.
     */
    void resetRanking();

    /**
     * sets the first player's colour.
     */
    void setFirstPlayerColour();

    /**
     * sets the second player's colour.
     */
    void setSecondPlayerColour();

    /**
     * sets the application's background.
     */
    void setBackground();
}
