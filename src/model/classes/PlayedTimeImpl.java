package model.classes;

import model.interfaces.PlayedTime;
import model.interfaces.Turn;

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
    public Double getElapsedTime() {
        return this.totalPlayTime;
    }

    @Override
    //CHECKSTYLE:OFF:
    public void setElapsedTime(final Double totalPlayTime) {
        //CHECKSTYLE:ON:
        this.totalPlayTime = totalPlayTime;
    }
}
