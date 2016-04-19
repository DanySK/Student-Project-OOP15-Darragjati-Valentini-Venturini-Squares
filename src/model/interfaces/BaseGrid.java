package model.interfaces;

import model.enumerations.GridOption;

/**
 * This interface is used to create a new playable field with size rowsNumber x
 * columnNumber. It offers the methods to insert a new move in the field, to get
 * which player has inserted a move, to set the current player turn,to get all
 * the possible moves and the remaining ones
 *
 */
public interface BaseGrid {

    /**
     * 
     * @return the player who should take the next move
     * @throws a
     *             IllegalStateException if the match is not started
     */
    GridOption getCurrentPlayerTurn();

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
    GridOption getCopyOfHorizontalElement(Integer listIndex, Integer position);

    /**
     * 
     * @param listIndex
     * @param position
     */
    void setHorizontalLine(int listIndex, int position, GridOption playerTurn);

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
     * @param playerTurn 
     */
    void setVerticalLine(final int listIndex, int position, GridOption playerTurn);

    void setPlayerTurn(GridOption turn);

    Integer getHorizontalListSize();

    Integer getVerticallListSize();
}
