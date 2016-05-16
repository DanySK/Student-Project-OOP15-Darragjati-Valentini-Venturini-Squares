package model.interfaces;

import model.enumerations.GridOption;
import model.exceptions.NoMovesDoneException;
import model.exceptions.UnexistentLineListException;

/**
 * This interface is used to manage the rotation between the players' turn.
 */
public interface Game {

    /**
     * Starts a new match.
     * 
     * @throws an IllegalStateException if the match is already started
     */
    void startMatch();

    /**
     * @return true if the game is started
     */
    boolean isStarted();

    /**
     * @return if there are no more moves left
     * @throws an IllegalStateException if the match is not started
     */
    boolean isEnded();

    /**
     * @return a defensive copy of the player that should make the next move
     * @throws an IllegalStateException if the match is not started
     */
    GridOption getCopyOfCurrentPlayerTurn();

    /**
     * Gets the score of one of the two players.
     * 
     * @param player
     *            is one of the players
     * @return his points
     * @throws a IllegalArgumentException if player does not exist
     */
    Integer getPlayerPoints(GridOption player);

    /**
     * 
     * @return the winner of the match, if the game is even returns GridOption.Empty
     * @throws an IllegalStateException if the game is not ended
     */
    GridOption getWinner();

    /**
     * Makes a move setting a line in the grid.
     * @param move the move that the player wants to do
     * @throws UnexistentLineListException if the listIndex input is not correct
     * @throws an UnsupportedOperationException if the list chosen is not supported by the game mode
     * @throws an IllegalArgumentException if the inserted parameters are not correct
     */
    void setLine(Move move) throws UnexistentLineListException;

    /**
     * Undo the last player move.
     * @throws NoMovesDoneException if no moves have been done yet
     * @throws UnexistentLineListException if the listIndex input is not correct
     * @throws an IllegalArgumentException if the the listof the last move does not exist
     */
    void undoLastMove() throws NoMovesDoneException, UnexistentLineListException;

    /**
     * @return a defensive copy of the last move.
     * @throws NoMovesDoneException if noone has made a move yet
     */
    Move getCopyOfLastMove() throws NoMovesDoneException;
}
