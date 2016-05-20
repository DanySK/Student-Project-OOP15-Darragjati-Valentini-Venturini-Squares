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
     * @return the player's winrate.
     */
    double getWinRate();

    /**
     * @return the player's total wins.
     */
    Integer getWonMatches();

    /**
     * @return the player's total matches.
     */
    Integer getTotalMatches();

    /**
     * @return the player's total points scored
     */
    Integer getTotalPointsScored();
}
