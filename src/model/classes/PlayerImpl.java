package model.classes;

import model.interfaces.Player;

/**
 * 
 * 
 *
 */
public class PlayerImpl implements Player {

    private String playerName;
    private double winRate;
    private Integer wonMatches;
    private Integer totalMatches;
    private Integer totalSquaresCatched;

    /**
     * 
     * @param playerName the player's name or nickname
     */
    public PlayerImpl(final String playerName) {

        this.playerName = playerName;
        this.winRate = 0;
        this.wonMatches = 0;
        this.totalMatches = 0;
        this.totalSquaresCatched = 0;
    }

    private void calculateWinRate() {

        double totalWins = (double) getTotalWins();
        double totalMatches = (double) getTotalMatches();
        this.winRate = totalWins / totalMatches;
    }

    @Override
    public String getPlayerName() {
        return playerName;
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
    public Integer getTotalMatches() {
        return this.totalMatches;
    }

    @Override
    public Integer getTotalSquaresCatched() {
        return this.totalSquaresCatched;
    }

    @Override
    public void addLastMatchResults(final boolean victory, final Integer totalSquaresCatched) {

        if (victory) {
            this.wonMatches++;
        }

        this.totalMatches++;

        calculateWinRate();

        this.totalSquaresCatched += totalSquaresCatched;
    }

    @Override
    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void setWonMatches(final Integer wonMatches) {
        this.wonMatches = wonMatches;
    }

    @Override
    public void setTotalMatches(final Integer totalMatches) {
        this.totalMatches = totalMatches;
    }

    @Override
    public void setTotalSquaresCatched(final Integer totalSquaresCatched) {
        this.totalSquaresCatched = totalSquaresCatched;
    }

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", winRate=" + winRate + ", wonMatches=" + wonMatches
                + ", totalMatches=" + totalMatches + ", totalSquaresCatched=" + totalSquaresCatched + "]";
    }

}
