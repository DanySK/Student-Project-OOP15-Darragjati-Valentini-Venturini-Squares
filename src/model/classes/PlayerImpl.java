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

    /**
     * This consctructor sets the fields of the class PlayerImpl.
     * @param playerName the name of the player
     * @param wonMatches his won mathces
     * @param totalMatches his total matches
     * @param totalPointsScored his total points scored
     */
    // CHECKSTYLE:OFF:
    public PlayerImpl(final String playerName, final Integer wonMatches, final Integer totalMatches, final Integer totalPointsScored) {
        // CHECKSTYLE:ON:
        this.playerName = playerName;
        this.wonMatches = wonMatches;
        this.totalMatches = totalMatches;
        checkCorrectInputs();
        this.totalPointsScored = totalPointsScored;
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
        this.winRate = getWonMatches() * 100 / (double) getTotalMatches();
    }

    @Override
    public double getWinRate() {
        calculateWinRate();
        return this.winRate;
    }

    private void checkCorrectInputs() {
        if (this.wonMatches > this.totalMatches) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Integer getWonMatches() {
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
