package model.interfaces;

import model.enumerations.GridOption;

/**
 * 
 * 
 *
 */
public interface BaseGrid {

    /**
     * Starts a new match.
     * @throws a IllegalStateException if the match is already started
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
     * 
     * @return the player who should take the next move
     * @throws a IllegalStateException if the match is not started
     */
    GridOption getCurrentPlayerTurn();

    /**
     * Gets the score of one of the two players.
     * @param player is one of the players
     * @return his points
     * @throws a IllegalArgumentException if player does not exist
     */
    Integer getPlayerPoints(GridOption player);

    /**
     * 
     * @return the number of moves
     */
    Integer getTotalMoves();

    /**
     * 
     * @return the number of moves left
     */
    Integer getRemainingMoves();

    /**
     * 
     * @param listIndex
     * @param position
     * @return
     */
    GridOption getCopyOfVerticalElement(Integer listIndex, Integer position);

    /**
     * 
     * @param listIndex
     * @param position
     */
    void setVerticalLine(int listIndex, int position);

    /**
     * 
     * @param listIndex
     * @param position
     * @return
     */
    GridOption getCopyOfHorizontalElement(Integer listIndex, Integer position);

    /**
     * 
     * @param listIndex
     * @param position
     */
    void setHorizontalLine(int listIndex, int position);

    /**
     * 
     * @return the winner of the match, if the game is even returns GridOption.Empty
     * @throws a IllegalStateException if the game is not ended
     */
    GridOption getWinner();
}
