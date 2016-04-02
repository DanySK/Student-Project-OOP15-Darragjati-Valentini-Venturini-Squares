package model.interfaces;

/**
 * 
 * 
 *
 */
public interface Player {

    /**
     * Get the player's name.
     * @return the player's name
     */
    String getPlayerName();

    /**
     * Get the player's winrate.
     * @return the player's winrate
     */
    double getWinRate();

    /**
     * Get the player's total wins.
     * @return the player's total wins
     */
    Integer getTotalWins();

    /**
     * Get the player's total matches.
     * @return the player's total matches
     */
    Integer getTotalMatches();

    /**
     * Get the player's total squares catched.
     * @return the player's total squaers catched
     */
    Integer getTotalSquaresCatched();

    /**
     * Adds the last match results to the player datas.
     * @param victory true if the player has won the last match
     * @param totalSquaresCatched the number of squares catched by the player in the last match
     */
    void addLastMatchResults(boolean victory, Integer totalSquaresCatched);

    /**
     * Set the player's name.
     * @param playerName the player's name
     */
    void setPlayerName(String playerName);

    /**
     * Set the player's total wins.
     * @param wonMatches the player's won matches
     */
    void setWonMatches(Integer wonMatches);

    /**
     * Set the player's total matches.
     * @param totalMatches the player's total matches
     */
    void setTotalMatches(Integer totalMatches);

    /**
     * Set the player's total squares catched.
     * @param totalSquaresCatched the player's total squares catched
     */
    void setTotalSquaresCatched(Integer totalSquaresCatched);


}
