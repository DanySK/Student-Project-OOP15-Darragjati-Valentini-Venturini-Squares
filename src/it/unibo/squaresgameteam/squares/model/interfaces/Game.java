package it.unibo.squaresgameteam.squares.model.interfaces;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.NoMovesDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;

/**
 * This interface is used to manage a match. The main methods the players can
 * choose are: to start a new game, to know if it is ended, to make a move, undo
 * a move and get who is the winner.
 */
public interface Game {

    /**
     * Starts a new match.
     */
    void startMatch();

    /**
     * @return true if the game is started
     */
    boolean isStarted();

    /**
     * @return if there are no more moves left
     */
    boolean isEnded();

    /**
     * @return the player that should make the next move
     */
    GridOption getCurrentPlayerTurn();

    /**
     * Gets the score of one of the two players.
     * 
     * @param player
     *            is one of the players
     * @return his points
     */
    Integer getPlayerPoints(GridOption player);

    /**
     * 
     * @return the winner of the match, if the game is even returns
     *         GridOption.Empty
     */
    GridOption getWinner();

    /**
     * This method returns the players match results one the game is ended.
     * 
     * @param player
     *            wich one of the two players you want to get
     * @return an object of the class player ready for being added in the
     *         ranking
     */
    Player getPlayerMatchResult(GridOption player);

    /**
     * 
     * @return the duration of the match
     */
    Double getMatchDuration();

    /**
     * Makes a move setting a line in the grid.
     * 
     * @param move
     *            the move that the player wants to do
     * @throws UnexistentLineListException
     *             if the move's listIndex is not correct
     * @throws UnsupportedOperationException
     *             if the list chosen is not supported by the game mode
     */
    void setLine(Move move) throws UnexistentLineListException;

    /**
     * Undo the last player move.
     * 
     * @throws NoMovesDoneException
     *             if no moves have been done yet
     * @throws UnexistentLineListException
     *             if the move's listIndex is not correct
     */
    void undoLastMove() throws NoMovesDoneException, UnexistentLineListException;

    /**
     * @return a defensive copy of the last move.
     * @throws NoMovesDoneException
     *             if noone has made a move yet
     */
    Move getCopyOfLastMove() throws NoMovesDoneException;
}
