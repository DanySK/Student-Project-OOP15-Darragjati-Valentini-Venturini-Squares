package model.interfaces;

import model.enumerations.GridOption;

/**
 * This interface is used to extend the functionalities of the interface
 * BaseGrid. It adds a new way to score points, setting a line in a diagonal way.
 */
public interface TriangleGrid extends BaseGrid {

    /**
     * @param listIndex is the number of the diagonal list where the player wants to get the line
     * @param position is the position of the chosen list where the player wants to get the line
     * @return which one of the two players has set the move
     */
    GridOption getCopyOfDiagonalElement(final int listIndex, int position);

    /**
     * @param listIndex is the number of the diagonal list where the player wants to set his line
     * @param position is the position of the chosen list where the player wants to set the line
     * @param playerTurn which one of the two players is making the move
     * @return the number of points scored by making a move
     */
    Integer setDiagonalLine(final int listIndex, int position, GridOption playerTurn);
   
    Integer getDiagonalListSize(); 
}
