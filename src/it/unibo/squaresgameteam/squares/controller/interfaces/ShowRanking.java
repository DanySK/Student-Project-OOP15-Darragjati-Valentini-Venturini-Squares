package it.unibo.squaresgameteam.squares.controller.interfaces;

import java.io.IOException;

import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;


/**
 * 
 * @author Licia Valentini
 * 
 *         Interface of class ShowRankingImpl.
 *
 */
public interface ShowRanking {
    /**
     * This method show the ranking.
     * 
     * @param rankingOrder
     *            sort order
     * @param reverse
     *            if you want the reverse
     * @return a string with the ranking.
     * @throws IOException
     *             .
     * @throws DuplicatedPlayerStatsException
     *             .
     * @throws ClassNotFoundException
     *             .
     */
    String showRanking(RankingOption rankingOrder, boolean reverse)
            throws IOException, DuplicatedPlayerStatsException, ClassNotFoundException;
    
    /**
     * This method resets the ranking.
     */

    void deleteRankingFile();    
    

}
