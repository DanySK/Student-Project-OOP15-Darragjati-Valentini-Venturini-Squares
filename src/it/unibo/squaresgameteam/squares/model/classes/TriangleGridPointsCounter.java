package it.unibo.squaresgameteam.squares.model.classes;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.PointsCounterStrategy;
import it.unibo.squaresgameteam.squares.model.interfaces.TriangleGrid;

/**
 * This class implements the interface PointsCounterStrategy. It is used to
 * calculate the points in a "triangle game" after a player has made a move.
 */
public class TriangleGridPointsCounter implements PointsCounterStrategy {

    private final TriangleGrid grid;
    private Integer pointsScored;
    
    /**
     * The consctructor takes in input the current playable grid.
     * 
     * @param grid
     *            the current grid
     */
    // CHECKSTYLE:OFF:
    public TriangleGridPointsCounter(final TriangleGrid grid) {
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
        if (listIndex > 0 && !grid.getVerticalLinePlayer(position, listIndex - 1).equals(GridOption.EMPTY)
                && !grid.getDiagonalLinePlayer(listIndex - 1, position)
                        .equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < grid.getHorizontalListSize() - 1
                && !grid.getVerticalLinePlayer(position + 1, listIndex).equals(GridOption.EMPTY)
                && !grid.getDiagonalLinePlayer(listIndex, position).equals(GridOption.EMPTY)) {
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
        if (listIndex > 0 && !grid.getHorizontalLinePlayer(position, listIndex - 1).equals(GridOption.EMPTY)
                && !grid.getDiagonalLinePlayer(position, listIndex - 1)
                        .equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < grid.getVerticalListSize() - 1
                && !grid.getHorizontalLinePlayer(position + 1, listIndex).equals(GridOption.EMPTY)
                && !grid.getDiagonalLinePlayer(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        this.pointsScored = points;
    }

    /**
     * This method calculates the player points after an diagonal line is set.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            the line
     * @throws UnsupportedOperationException
     *             if the grid doesn't support that action
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    protected void diagonalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (!grid.getHorizontalLinePlayer(listIndex, position).equals(GridOption.EMPTY)
                && !grid.getVerticalLinePlayer(position + 1, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        if (!grid.getHorizontalLinePlayer(listIndex + 1, position).equals(GridOption.EMPTY)
                && !grid.getVerticalLinePlayer(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        this.pointsScored = points;
    }
}
