package it.unibo.squaresgameteam.squares.model.interfaces;

/**
 * This interface is used to create a new playable field with size rowsNumber x
 * columnNumber. It offers the methods to insert a new move in the field, to get
 * which player has inserted a move, to get all the possible moves and the
 * remaining ones.
 */
public interface SquareGrid extends BaseGrid {

    /**
     * @return the number of horizontal lists that makes the grid.
     */
    Integer getHorizontalListSize();

    /**
     * @return the number of horizontal lists that makes the grid.
     */
    Integer getVerticalListSize();
}
