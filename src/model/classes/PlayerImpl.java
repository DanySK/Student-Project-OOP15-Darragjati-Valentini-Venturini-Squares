package model.classes;

import model.interfaces.Player;

/**
 * THis class implements the interface Player. It is used to manage the player's
 * informations.
 */
public class PlayerImpl implements Player {

    private String playerName;
    private Double winRate;
    private Integer wonMatches;
    private Integer totalMatches;
    private Integer totalSquaresCatched;

    // CHECKSTYLE:OFF:
    public PlayerImpl() {
        // CHECKSTYLE:ON:
        this.winRate = 0.0;
        this.wonMatches = 0;
        this.totalMatches = 0;
        this.totalSquaresCatched = 0;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setPlayerName(final String playerName) {
        // CHECKSTYLE:ON:
        this.playerName = playerName;
    }

    private void calculateWinRate() {
        this.winRate = getTotalWins() * 100 / (double) getTotalMatches();
    }

    @Override
    public double getWinRate() {
        calculateWinRate();
        System.out.println(this.winRate);
        return this.winRate;
    }

    private void checkCorrectInputs() {
        if (this.wonMatches > this.totalMatches) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Integer getTotalWins() {
        checkCorrectInputs();
        return this.wonMatches;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setWonMatches(final Integer wonMatches) {
        // CHECKSTYLE:ON:
        this.wonMatches = wonMatches;
    }

    @Override
    public Integer getTotalMatches() {
        checkCorrectInputs();
        return this.totalMatches;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setTotalMatches(final Integer totalMatches) {
        // CHECKSTYLE:ON:
        this.totalMatches = totalMatches;
    }

    @Override
    public Integer getTotalSquaresCatched() {
        return this.totalSquaresCatched;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setTotalSquaresCatched(final Integer totalSquaresCatched) {
        // CHECKSTYLE:ON:
        this.totalSquaresCatched = totalSquaresCatched;
    }

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", winRate=" + winRate + ", wonMatches=" + wonMatches
                + ", totalMatches=" + totalMatches + ", totalSquaresCatched=" + totalSquaresCatched + "]";
    }

}
