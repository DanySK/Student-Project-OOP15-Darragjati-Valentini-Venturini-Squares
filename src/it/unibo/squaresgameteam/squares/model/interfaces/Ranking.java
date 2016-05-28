package it.unibo.squaresgameteam.squares.model.interfaces;

import java.util.List;

import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;

/**
 * This interface assigns the last two players match results and organizes them
 * in a ranking. The ranking by default is ordered by winrate and can be
 * reordered in in other three different ways. The order can also be reverted.
 */
public interface Ranking {

    /**
     * Add a player last match results to the ranking.
     * 
     * @param player
     *            the player results that should be added to the ranking.
     */
    void addPlayerResults(Player player);

    /**
     * Reorders the list that memorizes the ranking in four differnt ways: per
     * winrate, per total wins, per total games played and per total squares
     * catched.
     * 
     * @param option
     *            wich way the list should be ordered
     * @param reverseRanking
     *            true if the list should be ordered in the opposite way
     */
    void orderListBy(RankingOption option, boolean reverseRanking);

    /**
     * 
     * @return the unmodifiable list
     */
    List<Player> getPlayerList();
}
