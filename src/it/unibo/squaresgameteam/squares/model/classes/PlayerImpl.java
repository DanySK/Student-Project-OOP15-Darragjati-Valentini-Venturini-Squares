package it.unibo.squaresgameteam.squares.model.classes;

import java.io.Serializable;

import it.unibo.squaresgameteam.squares.model.interfaces.Player;

/**
 * THis class implements the interface Player. It is used to manage the player's
 * informations.
 */
public final class PlayerImpl implements Player {

    
    
   
    private final String playerName;
    private Double winRate;
    private final Integer wonMatches;
    private final Integer totalMatches;
    private final Integer totalPointsScored;

    // CHECKSTYLE:OFF:
    private PlayerImpl(final String playerName, final Integer wonMatches, final Integer totalMatches,
            final Integer totalPointsScored) {
        // CHECKSTYLE:ON:
        this.playerName = playerName;
        this.wonMatches = wonMatches;
        this.totalMatches = totalMatches;
        checkCorrectInputs();
        calculateWinRate();
        this.totalPointsScored = totalPointsScored;
    }

    private void checkCorrectInputs() {
        if (this.wonMatches > this.totalMatches) {
            throw new IllegalArgumentException();
        }
    }

    private void calculateWinRate() {
        this.winRate = getWonMatches() * 100 / (double) getTotalMatches();
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public double getWinRate() {
        return this.winRate;
    }

    @Override
    public Integer getWonMatches() {
        return this.wonMatches;
    }

    @Override
    public Integer getTotalMatches() {
        return this.totalMatches;
    }

    @Override
    public Integer getTotalPointsScored() {
        return this.totalPointsScored;
    }

    /**
     * This Builder is used to configure all the needed fields to create a new
     * player.
     *
     */
    //CHECKSTYLE:OFF:
    public static class Builder {
        private String playerName;
        private Integer wonMatches;
        private Integer totalMatches;
        private Integer totalPointsScored;

        /**
         * @param playerName
         *            the player's name
         * @return this
         */
        public Builder playerName(final String playerName) {
            this.playerName = playerName;
            return this;
        }

        /**
         * 
         * @param wonMatches
         *            the player's won matches
         * @return this
         */
        public Builder wonMatches(final Integer wonMatches) {
            this.wonMatches = wonMatches;
            return this;
        }

        /**
         * 
         * @param totalMatches
         *            the player's total matches
         * @return this
         */
        public Builder totalMatches(final Integer totalMatches) {
            this.totalMatches = totalMatches;
            return this;
        }

        /**
         * 
         * @param totalPointsScored
         *            the player's total points scored
         * @return this
         */
        public Builder totalPointsScored(final Integer totalPointsScored) {
            this.totalPointsScored = totalPointsScored;
            return this;
        }

        /**
         * @return a new PlayerImpl
         */
        public PlayerImpl build() {
            if (this.playerName == null || this.wonMatches == null || this.totalMatches == null
                    || this.totalPointsScored == null) {
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
