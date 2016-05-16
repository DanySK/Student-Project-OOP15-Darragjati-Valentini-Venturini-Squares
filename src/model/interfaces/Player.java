package model.interfaces;

/**
 * This interface is used to organize the player's statistics. It offers getter
 * and setters for all his fields except his winrate. It has only the getter
 * because it is calculated every time everything is up to date.
 */
public interface Player {

    /**
     * @return the player's name.
     */
    String getPlayerName();

    /**
     * @param playerName the player's name
     */
    void setPlayerName(String playerName);

    /**
     * @return the player's winrate.
     */
    double getWinRate();

    /**
     * @return the player's total wins.
     */
    Integer getWonMatches();

    /**
     * @param wonMatches the player's won matches
     */
    void setWonMatches(Integer wonMatches);

    /**
     * @return the player's total matches.
     */
    Integer getTotalMatches();

    /**
     * @param totalMatches the player's total matches
     */
    void setTotalMatches(Integer totalMatches);

    /**
     * @return the player's total points scored
     */
    Integer getTotalPointsScored();

    /**
     * @param totalSquaresCatched the player's total squares catched
     */
    void setTotalPointsScored(Integer totalSquaresCatched);
}
