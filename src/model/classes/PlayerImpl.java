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
    private Integer totalPointsScored;

    // CHECKSTYLE:OFF:
    public PlayerImpl() {
        // CHECKSTYLE:ON:
        this.winRate = 0.0;
        this.wonMatches = 0;
        this.totalMatches = 0;
        this.totalPointsScored = 0;
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
        return this.wonMatches;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setWonMatches(final Integer wonMatches) {
        // CHECKSTYLE:ON:
        checkCorrectInputs();
        this.wonMatches = wonMatches;
    }

    @Override
    public Integer getTotalMatches() {
        return this.totalMatches;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setTotalMatches(final Integer totalMatches) {
        // CHECKSTYLE:ON:
        checkCorrectInputs();
        this.totalMatches = totalMatches;
    }

    @Override
    public Integer getTotalPointsScored() {
        return this.totalPointsScored;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setTotalPointsScored(final Integer totalPointsScored) {
        // CHECKSTYLE:ON:
        this.totalPointsScored = totalPointsScored;
    }

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", winRate=" + winRate + ", wonMatches=" + wonMatches
                + ", totalMatches=" + totalMatches + ", totalPointScored=" + totalPointsScored + "]";
    }

}
