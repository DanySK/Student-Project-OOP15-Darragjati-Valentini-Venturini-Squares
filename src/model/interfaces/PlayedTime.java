package model.interfaces;

/**
 * This interface is used to calculate the total time spent playing the game.
 */
public interface PlayedTime {

    /**
     * This method gets the current time when a game starts.
     * 
     * @param currentGame
     *            the current running game, it is used to verify if it is
     *            started.
     */
    void gameStarted(Turn currentGame);

    /**
     * This method calculates the duration of the game.
     * 
     * @param currentGame
     *            the current running game, it is used to verify if it is ended.
     */
    void gameEnded(Turn currentGame);

    /**
     * @return the total play time after the last match.
     */
    Double getTotalElapsedTime();
}
