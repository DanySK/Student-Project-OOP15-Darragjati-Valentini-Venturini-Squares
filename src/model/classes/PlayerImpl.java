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
     * 
     * @param playerName
     *            the name of the player
     * @param wonMatches
     *            his won mathces
     * @param totalMatches
     *            his total matches
     * @param totalPointsScored
     *            his total points scored
     */
    // CHECKSTYLE:OFF:
    public PlayerImpl(final String playerName, final Integer wonMatches, final Integer totalMatches,
            final Integer totalPointsScored) {
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

    /**
     * @param playerName the player's name
     */
    // CHECKSTYLE:OFF:
    protected void setPlayerName(final String playerName) {
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

    /**
     * @param wonMatches the player's won matches
     */
    // CHECKSTYLE:OFF:
    protected void setWonMatches(final Integer wonMatches) {
        // CHECKSTYLE:ON:
        checkCorrectInputs();
        this.wonMatches = wonMatches;
    }

    @Override
    public Integer getTotalMatches() {
        return this.totalMatches;
    }

    
    /**
     * @param totalMatches the player's total matches
     */
    // CHECKSTYLE:OFF:
    protected void setTotalMatches(final Integer totalMatches) {
        // CHECKSTYLE:ON:
        checkCorrectInputs();
        this.totalMatches = totalMatches;
    }

    @Override
    public Integer getTotalPointsScored() {
        return this.totalPointsScored;
    }

    
    /**
     * @param totalPointsScored the player's total squares catched
     */
    // CHECKSTYLE:OFF:
    protected void setTotalPointsScored(final Integer totalPointsScored) {
        // CHECKSTYLE:ON:
        this.totalPointsScored = totalPointsScored;
    }

    public static class Builder {
        private String playerName;
        private Integer wonMatches;
        private Integer totalMatches;
        private Integer totalPointsScored;

        public Builder playerName(final String playerName) {
            this.playerName = playerName;
            return this;
        }

        public Builder wonMatches(final Integer wonMatches) {
            this.wonMatches = wonMatches;
            return this;
        }

        public Builder totalMatches(final Integer totalMatches) {
            this.totalMatches = totalMatches;
            return this;
        }

        public Builder totalPointsScored(final Integer totalPointsScored) {
            this.totalPointsScored = totalPointsScored;
            return this;
        }

        public PlayerImpl build(){
            if(this.playerName == null || this.wonMatches == null || this.totalMatches== null || this.totalPointsScored == null){
                throw new IllegalStateException();
            }
            return new PlayerImpl(this.playerName, this.wonMatches, this.totalMatches, this.totalPointsScored);
        }
    }

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", winRate=" + winRate + ", wonMatches=" + wonMatches
                + ", totalMatches=" + totalMatches + ", totalPointScored=" + totalPointsScored + "]";
    }

}
