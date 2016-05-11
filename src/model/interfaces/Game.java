package model.interfaces;

import model.enumerations.GridOption;
import model.enumerations.ListType;

/**
 * This interface is used to manage the rotation between the players' turn.
 */
public interface Game {

    /**
     * Starts a new match.
     * 
     * @throws a
     *             IllegalStateException if the match is already started
     */
    void startMatch();

    /**
     * 
     * @return true if the game is started
     */
    boolean isStarted();

    /**
     * 
     * @return if there are no more moves left
     */
    boolean isEnded();
    
    /**
     * Gets the player tha should make the next move.
     * @return the player that should make the next move
     */
    GridOption getCopyOfCurrentPlayerTurn();

    /**
     * Gets the score of one of the two players.
     * 
     * @param player
     *            is one of the players
     * @return his points
     * @throws a
     *             IllegalArgumentException if player does not exist
     */
    Integer getPlayerPoints(GridOption player);

    /**
     * 
     * @return the winner of the match, if the game is even returns
     *         GridOption.Empty
     * @throws a
     *             IllegalStateException if the game is not ended
     */
    GridOption getWinner();

    /**
     * Makes a move setting a line in the grid.
     * 
     * @param list
     *            wich one between the horizontal and the vertical list is
     *            chosen to make the move
     * @param listIndex
     *            is the number of the list where the player wants to set his
     *            line
     * @param position
     *            is the position of the chosen list where the player wants to
     *            set the line
     * @throws a
     *             IllegalArgumentException if the parameters insert are not
     *             correct
     */
    void setLine(ListType list, Integer listIndex, Integer position);

    /**
     * Undo the last player move.
     * 
     * @throws a
     *             IllegalStateException if no moves are done
     */
    void undoLastMove();

    /**
     * 
     * @return the copy of the last move.
     */
    LastMove getCopyOfLastMove();
}
