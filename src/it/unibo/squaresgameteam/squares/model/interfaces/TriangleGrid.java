package it.unibo.squaresgameteam.squares.model.interfaces;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;

/**
 * This interface is used to extend the functionalities of the interface
 * SquareGrid. It adds a new way to score points, setting a line in a diagonal
 * way.
 */
public interface TriangleGrid extends SquareGrid {

    /**
     * @param listIndex
     *            is the number of the diagonal list where the player wants to
     *            get the line
     * @param position
     *            is the position of the chosen list where the player wants to
     *            get the line
     * @return which one of the two players has set the move
     * @throws UnexistentLineListException
     *             if the rowsNumber or the coulumnsNumber aren't correct
     */
    GridOption getDiagonalLinePlayer(final int listIndex, int position) throws UnexistentLineListException;

    /**
     * @return the number of diagonal lists that makes the grid.
     */
    Integer getDiagonalListSize();
}
