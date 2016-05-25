package it.unibo.squaresgameteam.squares.model.interfaces;

import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;

/**
 * This interface is used get a player's points after he has done a move.
 */
public interface PointsCounterStrategy {

    /**
     * This method is used to know if a move has scored a point.
     * 
     * @param move the move that the player has made
     * @return the points scored
     * @throws UnexistentLineListException 
     */
    Integer getPoints(Move move) throws UnexistentLineListException;
}
