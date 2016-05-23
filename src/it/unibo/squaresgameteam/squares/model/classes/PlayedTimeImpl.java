package it.unibo.squaresgameteam.squares.model.classes;

import it.unibo.squaresgameteam.squares.model.interfaces.PlayedTime;

/**
 * This class implements the methods of the interface PlayedTime. It offers the
 * methods to calculate the time of a match.
 */
public class PlayedTimeImpl implements PlayedTime {

    private Double totalPlayTime;
    private long startGameTime;

    /**
     * This constructor sets the fields of the object with default values.
     */
    public PlayedTimeImpl() {
        this.totalPlayTime = -1.0;
        this.startGameTime = -1;
    }

    /**
     * This method gets the current time when a game starts.
     * 
     * @param isStarted
     *            this parameter is used to verify if the current game is
     *            started.
     * @throws an
     *             IllegalStateException if the match is not started.
     */
    protected void setTimeAtMatchStart(final boolean isStarted) {
        if (isStarted && this.startGameTime == -1) {
            this.startGameTime = System.currentTimeMillis();
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * This method calculates the duration of the game.
     * 
     * @param isEnded
     *            this parameter is used to verify if the current game is ended.
     * @throws an
     *             IllegalStateException if the match is not ended
     */
    protected void calculateMatchDuration(final boolean isEnded) {
        if (isEnded) {
            this.totalPlayTime = (this.startGameTime - System.currentTimeMillis()) / 1000.0;
        } else {
            throw new IllegalStateException();
        }

    }

    @Override
    public Double getTotalMatchDuration() {
        if (this.totalPlayTime.equals(-1.0)) {
            throw new IllegalStateException();
        }
        return this.totalPlayTime;
    }
}
