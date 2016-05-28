package it.unibo.squaresgameteam.squares.model.interfaces;

/**
 * This interface is used to extend the functionalities of the interface
 * SquareGrid. It adds a new way to score points, setting a line in a diagonal
 * way.
 */
public interface TriangleGrid extends SquareGrid {

    /**
     * @return the number of diagonal lists that makes the grid.
     */
    Integer getDiagonalListSize();
}
