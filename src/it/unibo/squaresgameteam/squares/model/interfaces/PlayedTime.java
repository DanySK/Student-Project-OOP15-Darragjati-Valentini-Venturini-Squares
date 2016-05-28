package it.unibo.squaresgameteam.squares.model.interfaces;

/**
 * This interface is used to calculate the total time spent playing the game.
 */
public interface PlayedTime {

    /**
     * @return the total play time after the match is ended.
     * @throws an IllegalStateException if the method calculateMatchDuration wasn't called.
     */
    Double getTotalMatchDuration();
}
