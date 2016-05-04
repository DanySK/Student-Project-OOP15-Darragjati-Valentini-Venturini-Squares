package model.classes;

import model.interfaces.PlayedTime;
import model.interfaces.Turn;

/**
 * This class implements the methods of the interface PlayedTime. It offers the
 * methods to calculate the time of a match.
 */
public class PlayedTimeImpl implements PlayedTime {

    private Double totalPlayTime = 0.0;
    private long startGameTime = 0;

    public PlayedTimeImpl() {
    }

    @Override
    public void gameStarted(final Turn currentGame) {
        if (currentGame.isEnded()) {
            this.startGameTime = System.currentTimeMillis();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void gameEnded(final Turn currentGame) {
        if (currentGame.isEnded()) {
            Double currentGamePlayTime;
            currentGamePlayTime = (this.startGameTime - System.currentTimeMillis()) / 1000.0;
            this.totalPlayTime += currentGamePlayTime;
        } else {
            throw new IllegalStateException();
        }

    }

    @Override
    public Double getTotalElapsedTime() {
        return this.totalPlayTime;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setElapsedTime(final Double totalPlayTime) {
        // CHECKSTYLE:ON:
        this.totalPlayTime = totalPlayTime;
    }
}
