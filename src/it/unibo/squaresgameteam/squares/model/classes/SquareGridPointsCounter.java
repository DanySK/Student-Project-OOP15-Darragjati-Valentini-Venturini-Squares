package it.unibo.squaresgameteam.squares.model.classes;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.PointsCounterStrategy;
import it.unibo.squaresgameteam.squares.model.interfaces.SquareGrid;

/**
 * This class implements the interface PointsCounterStrategy. It is used to
 * calculate the points in a "square game" after a player has made a move.
 */
public class SquareGridPointsCounter implements PointsCounterStrategy {

    private final SquareGrid grid;
    private Integer pointsScored;
    /**
     * The consctructor takes in input the current playable grid.
     * 
     * @param grid
     *            the current grid
     */
    // CHECKSTYLE:OFF:
    public SquareGridPointsCounter(final SquareGrid grid) {
        // CHECKSTYLE:ON:
        this.grid = grid;
    }

    @Override
    public Integer getPoints() {      
        return this.pointsScored;
    }
    
    /**
     * This method calculates the player points after an horizontal line is set.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            the line
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    protected void horizontalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (listIndex > 0 && !grid.getHorizontalLinePlayer(listIndex - 1, position).equals(GridOption.EMPTY)
                && !grid.getVerticalLinePlayer(position, listIndex - 1).equals(GridOption.EMPTY)
                && !grid.getVerticalLinePlayer(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < grid.getHorizontalListSize() - 1
                && !grid.getHorizontalLinePlayer(listIndex + 1, position).equals(GridOption.EMPTY)
                && !grid.getVerticalLinePlayer(position, listIndex - 1).equals(GridOption.EMPTY)
                && !grid.getVerticalLinePlayer(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
            points++;

        }
        this.pointsScored = points;
    }

    /**
     * This method calculates the player points after an vertical line is set.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            the line
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    protected void verticalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (listIndex > 0 && !grid.getVerticalLinePlayer(listIndex - 1, position).equals(GridOption.EMPTY)
                && !grid.getHorizontalLinePlayer(position, listIndex - 1).equals(GridOption.EMPTY)
                && !grid.getHorizontalLinePlayer(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < grid.getVerticalListSize() - 1
                && !grid.getVerticalLinePlayer(listIndex + 1, position).equals(GridOption.EMPTY)
                && !grid.getHorizontalLinePlayer(position, listIndex - 1).equals(GridOption.EMPTY)
                && !grid.getHorizontalLinePlayer(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
            points++;
        }
        this.pointsScored = points;
    }
}
