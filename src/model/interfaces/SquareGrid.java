package model.interfaces;

import model.enumerations.GridOption;

/**
 * This interface is used to create a new playable field with size rowsNumber x
 * columnNumber. It offers the methods to insert a new move in the field, to get
 * which player has inserted a move, to set the current player turn,to get all
 * the possible moves and the remaining ones.
 */
public interface SquareGrid {

    /**
     * @return the number of moves
     */
    Integer getTotalMoves();

    /**
     * @return the number of moves left
     */
    Integer getRemainingMoves();

    /**
     * @param listIndex is the number of the horizontal list where the player wants to get the line
     * @param position is the index of the chosen list where the player wants to get the line
     * @return which one of the two players has set the move
     */
    GridOption getCopyOfHorizontalElement(Integer listIndex, Integer position);

    /**
     * This method makes a player make a horizontal move.
     * @param listIndex is the number of the horizontal list where the player wants to set his line
     * @param position is the index of the chosen list where the player wants to set the line
     * @param playerTurn which one of the two players is making the move
     * @return the number of points scored by making a move 
     */
    Integer setHorizontalLine(int listIndex, int position, GridOption playerTurn);
    
    /**
     * @param listIndex is the number of the vertical list where the player wants to get the line
     * @param position is the index of the chosen list where the player wants to get the line
     * @return which one of the two players has set the move
     */
    GridOption getCopyOfVerticalElement(Integer listIndex, Integer position);

    /**
     * This method makes a player make a vertical move.
     * @param listIndex is the number of the vertical list where the player wants to set his line
     * @param position is the index of the chosen list where the player wants to set the line
     * @param playerTurn which one of the two players is making the move
     * @return the number of points scored by making a move
     */
    Integer setVerticalLine(final int listIndex, int position, GridOption playerTurn);
    
    /**
     * @return the number of horizontal lists that makes the grid.
     */
    Integer getHorizontalListSize();

    /**
     * @return the number of horizontal lists that makes the grid.
     */
    Integer getVerticallListSize();
}
