package model.interfaces;

import model.enumerations.GridOption;

/**
 * 
 * 
 *
 */
public interface BaseGrid {

    
    /**
     * 
     * @return the player who should take the next move
     * @throws a IllegalStateException if the match is not started
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
}
