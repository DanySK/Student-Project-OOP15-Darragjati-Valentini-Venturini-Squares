package model.interfaces;

import model.enumerations.GridOption;

/**
 * This interface is used to create a new playable field with size rowsNumber x
 * columnNumber. It offers the methods to insert a new move in the field, to get
 * which player has inserted a move, to set the current player turn,to get all
 * the possible moves and the remaining ones.
 */
public interface BaseGrid {

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
     * @param listIndex is the number of the horizontal list where the player wants to get the line
     * @param position is the position of the chosen list where the player wants to get the line
     * @return which one of the two players has set the move
     */
    GridOption getCopyOfHorizontalElement(Integer listIndex, Integer position);

    /**
     *
     * @param listIndex is the number of the horizontal list where the player wants to set his line
     * @param position is the position of the chosen list where the player wants to set the line
     * @param playerTurn which one of the two players is making the move
     */
    Integer setHorizontalLine(int listIndex, int position, GridOption playerTurn);
    
    /**
     * @param listIndex is the number of the vertical list where the player wants to get the line
     * @param position is the position of the chosen list where the player wants to get the line
     * @return which one of the two players has set the move
     */
    GridOption getCopyOfVerticalElement(Integer listIndex, Integer position);

    /**
     * 
     * @param listIndex is the number of the vertical list where the player wants to set his line
     * @param position is the position of the chosen list where the player wants to set the line
     * @param playerTurn which one of the two players is making the move
     */
    Integer setVerticalLine(final int listIndex, int position, GridOption playerTurn);
    
    /**
     * Gets the number of horizontal lists that makes the grid.
     * @return the number of horizontal lists that makes the grid.
     */
    Integer getHorizontalListSize();

    /**
     * Gets the number of vertical lists that makes the grid.
     * @return the number of horizontal lists that makes the grid.
     */
    Integer getVerticallListSize();
}
