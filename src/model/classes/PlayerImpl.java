package model.classes;

import model.interfaces.Player;


public class PlayerImpl implements Player {

    private String playerName;
    private double winRate;
    private Integer wonMatches;
    private Integer totalMatches;
    private Integer totalSquaresCatched;

    // CHECKSTYLE:OFF:
    public PlayerImpl(final String playerName) {
        // CHECKSTYLE:ON:
        this.playerName = playerName;
        this.winRate = 0;
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
        this.winRate = getTotalWins() / (double) getTotalMatches();
    }
    
    @Override
    public double getWinRate() {
        calculateWinRate();
        return this.winRate;
    }

    @Override
    public Integer getTotalWins() {
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
    // CHECKSTYLE:OFF:
    public void addLastMatchResults(final boolean victory, final Integer totalSquaresCatched) {
        // CHECKSTYLE:ON:
        if (victory) {
            this.wonMatches++;
        }
        this.totalMatches++;
        calculateWinRate();
        this.totalSquaresCatched += totalSquaresCatched;
    }

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", winRate=" + winRate + ", wonMatches=" + wonMatches
                + ", totalMatches=" + totalMatches + ", totalSquaresCatched=" + totalSquaresCatched + "]";
    }

}
