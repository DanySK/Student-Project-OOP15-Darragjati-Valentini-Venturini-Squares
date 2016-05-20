package it.unibo.squaresgameteam.squares.model.interfaces;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;

/**
 * This interface is used to create a new playable field with size rowsNumber x
 * columnNumber. It offers the methods to insert a new move in the field, to get
 * which player has inserted a move, to get all the possible moves and the
 * remaining ones.
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
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            get the line
     * @param position
     *            is the index of the chosen list where the player wants to get
     *            the line
     * @return which one of the two players has set the move
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    GridOption getCopyOfHorizontalElement(Integer listIndex, Integer position) throws UnexistentLineListException;

    /**
     * @param listIndex
     *            is the number of the vertical list where the player wants to
     *            get the line
     * @param position
     *            is the index of the chosen list where the player wants to get
     *            the line
     * @return which one of the two players has set the move
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    GridOption getCopyOfVerticalElement(Integer listIndex, Integer position) throws UnexistentLineListException;

    /**
     * @return the number of horizontal lists that makes the grid.
     */
    Integer getHorizontalListSize();

    /**
     * @return the number of horizontal lists that makes the grid.
     */
    Integer getVerticalListSize();
}
