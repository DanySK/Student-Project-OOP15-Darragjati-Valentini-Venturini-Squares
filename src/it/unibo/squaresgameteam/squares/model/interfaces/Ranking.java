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
     * Add the last match results.
     * 
     * @param playerName  the player's name
     * @param win  if the player has won the last match
     * @param score the players score
     */
    void addPlayerResults(String playerName, boolean win, Integer score);

    /**
     * Reorders the list that memorizes the ranking in four differnt ways: per
     * winrate, per total wins, per total games played and per total squares
     * catched.
     * 
     * @param option wich way the list should be ordered
     * @param reverseRanking true if the list should be ordered in the opposite way
     * @return the reordered list
     * @throws an IllegalArgumentException if the ordering option does not exist
     */
    List<Player> orderListBy(RankingOption option, boolean reverseRanking);
}
