package model.interfaces;

/**
 * This interface is used to organize the player's statistics. It offers getter
 * and setters for all his fields except his winrate that is calculated every
 * time everything is up to date.
 */
public interface Player {

    /**
     * Get the player's name.
     * 
     * @return the player's name
     */
    String getPlayerName();

    /**
     * Set the player's name.
     * 
     * @param playerName
     *            the player's name
     */
    void setPlayerName(String playerName);

    /**
     * Get the player's winrate.
     * 
     * @return the player's winrate
     */
    double getWinRate();

    /**
     * Get the player's total wins.
     * 
     * @return the player's total wins
     */
    Integer getTotalWins();

    /**
     * Set the player's total wins.
     * 
     * @param wonMatches
     *            the player's won matches
     */
    void setWonMatches(Integer wonMatches);

    /**
     * Get the player's total matches.
     * 
     * @return the player's total matches
     */
    Integer getTotalMatches();

    /**
     * Set the player's total matches.
     * 
     * @param totalMatches
     *            the player's total matches
     */
    void setTotalMatches(Integer totalMatches);

    /**
     * Get the player's total squares catched.
     * 
     * @return the player's total squaers catched
     */
    Integer getTotalSquaresCatched();

    /**
     * Set the player's total squares catched.
     * 
     * @param totalSquaresCatched
     *            the player's total squares catched
     */
    void setTotalSquaresCatched(Integer totalSquaresCatched);
}
