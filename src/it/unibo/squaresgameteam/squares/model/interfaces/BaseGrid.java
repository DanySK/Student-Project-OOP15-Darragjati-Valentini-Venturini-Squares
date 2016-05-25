package it.unibo.squaresgameteam.squares.model.interfaces;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;

/**
 * This interface has the base methods needed to create a new game mode. It must
 * be extended by other interfaces that should provide the methods to make
 * moves.
 */
public interface BaseGrid {

    /**
     * @return the number of moves
     */
    Integer getTotalMoves();

    /**
     * @return the number of moves left
     */
    Integer getRemainingMoves();

    /**
     * This method is used to know the maximum points that can be done in a
     * game.
     * 
     * @return the maximum points that can be done in this game
     */
    Integer getMatchMaximumPoints();

    /**
     * This method makes a player make a move.
     * 
     * @param move
     *            the players move
     * @param currentPlayerTurn
     *            wich one of the two players is making the move
     * @throws UnexistentLineListException
     *             if the listIndex of the move is not correct
     */
    void setLine(Move move, GridOption currentPlayerTurn) throws UnexistentLineListException;
}
