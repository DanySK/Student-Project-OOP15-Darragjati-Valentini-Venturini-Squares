package model.interfaces;

public interface Player {

    /**
     * Get the player's name
     * @return
     */
    String getPlayerName();

    /**
     * Get the player's winrate
     * @return
     */
    double getWinRate();

    /**
     * Get the player's total wins
     * @return
     */
    Integer getTotalWins();

    /**
     * Get the player's total matches
     * @return
     */
    Integer getTotalMatches();

    /**
     * Get the player's total squares catched
     * @return
     */
    Integer getTotalSquaresCatched();

    /**
     * Adds the last match results to the player datas
     * @param victory true if the player has won the last match
     * @param totalSquaresCatched the number of squares catched by the player in the last match
     */
    void addLastMatchResults(boolean victory, Integer totalSquaresCatched);

    /**
     * Set the player's name
     * @param playerName
     */
    void setPlayerName(String playerName);

    /**
     * Set the player's total wins
     * @param wonMatches
     */
    void setWonMatches(Integer wonMatches);

    /**
     * Set the player's total matches
     * @param totalMatches
     */
    void setTotalMatches(Integer totalMatches);

    /**
     * Set the player's total squares catched
     * @param totalSquaresCatched
     */
    void setTotalSquaresCatched(Integer totalSquaresCatched);


}
