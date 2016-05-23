package it.unibo.squaresgameteam.squares.model.interfaces;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;

/**
 * This interface is used to create a new playable field with size rowsNumber x
 * columnNumber. It offers the methods to insert a new move in the field, to get
 * which player has inserted a move, to get all the possible moves and the
 * remaining ones.
 */
public interface SquareGrid extends BaseGrid {

    /**
     * This method is used to know which one of the two players has set the
     * horizontal line.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            get the line
     * @param position
     *            is the index of the chosen list where the player wants to get
     *            the line
     * @return which one of the two players has set the move, it is
     *         GridOption.Empty if noone has set the line
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    GridOption getHorizontalLinePlayer(Integer listIndex, Integer position) throws UnexistentLineListException;

    /**
     * This method is used to know which one of the two players has set the
     * vertical line.
     * 
     * @param listIndex
     *            is the number of the vertical list where the player wants to
     *            get the line
     * @param position
     *            is the index of the chosen list where the player wants to get
     *            the line
     * @return which one of the two players has set the move, it is
     *         GridOption.Empty if noone has set the line
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    GridOption getVerticalLinePlayer(Integer listIndex, Integer position) throws UnexistentLineListException;

    /**
     * @return the number of horizontal lists that makes the grid.
     */
    Integer getHorizontalListSize();

    /**
     * @return the number of horizontal lists that makes the grid.
     */
    Integer getVerticalListSize();
}
