package model.interfaces;

import java.util.List;

import model.classes.PlayerImpl;
import model.enumerations.RankingOption;

public interface Ranking {

    /**
     * Add the last match results
     * @param playerName 
     * @param win
     * @param score
     */
    public void addPlayerResults(String playerName, boolean win, Integer score);
    
    /**
     * Reorders the list that memorizes the ranking in four differnt ways: per winrate, per total wins, per total games played and per total squares catched
     * @param option wich way the list should be ordered
     * @return the reordered list
     */
    public List<PlayerImpl> orderListBy(RankingOption option) ;
       
    /**
     * Reverts the order of the list obtained through the method addPlayerResults
     * @param option wich way the list should be ordered
     * @return the reordered list
     */
    public List<PlayerImpl> reverseRanking(RankingOption option);
}
