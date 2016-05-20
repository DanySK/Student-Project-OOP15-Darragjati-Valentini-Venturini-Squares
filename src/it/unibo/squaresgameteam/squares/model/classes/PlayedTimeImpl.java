package it.unibo.squaresgameteam.squares.model.classes;

import it.unibo.squaresgameteam.squares.model.interfaces.PlayedTime;
import it.unibo.squaresgameteam.squares.model.interfaces.Game;

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
        this.totalPlayTime = 0.0;
        this.startGameTime = 0;
    }

    @Override
    public void setTimeAtMatchStart(final Game currentGame) {
        if (currentGame.isStarted() && this.startGameTime != 0) {
            this.startGameTime = System.currentTimeMillis();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void calculateMatchDuration(final Game currentGame) {
        if (currentGame.isEnded()) {
            Double currentGamePlayTime;
            currentGamePlayTime = (this.startGameTime - System.currentTimeMillis()) / 1000.0;
            this.totalPlayTime += currentGamePlayTime;
        } else {
            throw new IllegalStateException();
        }

    }

    @Override
    public Double getTotalMatchDuration() {
        if (this.totalPlayTime.equals(0.0)) {
            throw new IllegalStateException();
        }
        return this.totalPlayTime;
    }
}
