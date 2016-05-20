package it.unibo.squaresgameteam.squares.model.interfaces;

/**
 * This interface is used to calculate the total time spent playing the game.
 */
public interface PlayedTime {

    /**
     * This method gets the current time when a game starts.
     * @param currentGame the current running game, it is used to verify if it is started.
     * @throws an IllegalStateException if the match is not started.
     */
    void setTimeAtMatchStart(Game currentGame);

    /**
     * This method calculates the duration of the game.
     * 
     * @param currentGame the current running game, it is used to verify if it is ended.
     * @throws an IllegalStateException if the match is not ended
     */
    void calculateMatchDuration(Game currentGame);

    /**
     * @return the total play time after the last match.
     * @throws an IllegalStateException if the method calculateMatchDuration wasn't called.
     */
    Double getTotalMatchDuration();
}
