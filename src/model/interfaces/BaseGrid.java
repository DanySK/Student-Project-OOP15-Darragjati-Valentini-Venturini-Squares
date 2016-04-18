package model.interfaces;

import model.classes.LastMoveImpl;
import model.enumerations.GridOption;

/**
 * 
 * CHE SCRIVERE?
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
    void setVerticalLine(int listIndex, int position, GridOption playerTurn);

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

    LastMove getCopyOfLastMove();

    void setPlayerTurn(GridOption turn);

    Integer getHorizontalListSize();

    Integer getVerticallListSize();

    LastMove getLastMove();
}
