package model.classes;

import model.interfaces.Player;

public class PlayerImpl implements Player{

    private String playerName;
    private double winRate;
    private Integer wonMatches;
    private Integer totalMatches;
    private Integer totalSquaresCatched;

    public PlayerImpl(String playerName) {

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

    public String getPlayerName() {
        return playerName;
    }

    public double getWinRate() {
        calculateWinRate();
        return this.winRate;
    }

    public Integer getTotalWins() {
        return this.wonMatches;
    }

    public Integer getTotalMatches() {
        return this.totalMatches;
    }

    public Integer getTotalSquaresCatched() {
        return this.totalSquaresCatched;
    }

    public void addLastMatchResults(boolean victory, Integer totalSquaresCatched) {

        if (victory) {
            this.wonMatches++;
        }

        this.totalMatches++;

        calculateWinRate();

        this.totalSquaresCatched += totalSquaresCatched;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public void setWonMatches(Integer wonMatches) {
        this.wonMatches = wonMatches;
    }
    
    public void setTotalMatches(Integer totalMatches) {
        this.totalMatches = totalMatches;
    }
    
    public void setTotalSquaresCatched(Integer totalSquaresCatched) {
        this.totalSquaresCatched = totalSquaresCatched;
    }

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", winRate=" + winRate + ", wonMatches=" + wonMatches
                + ", totalMatches=" + totalMatches + ", totalSquaresCatched=" + totalSquaresCatched + "]";
    }
    
    
}
