package model.classes;

import model.enumerations.GridOption;
import model.exceptions.UnexistentLineListException;
import model.interfaces.PointsCounterStrategy;
import model.interfaces.SquareGrid;

/**
 * This class is used to calculate the points after a player has made a move.
 */
public class SquareGridPointsCounterImpl implements PointsCounterStrategy {

    private final SquareGrid grid;

    /**
     * The consctructor takes in input the current playable grid.
     * 
     * @param grid
     *            the current grid
     */
    // CHECKSTYLE:OFF:
    public SquareGridPointsCounterImpl(final SquareGrid grid) {
        // CHECKSTYLE:ON:
        this.grid = grid;
    }

    /**
     * This method calculate the player points after an horizontal line is set.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            the line
     * @return the points scored
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    @Override
    public Integer horizontalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (listIndex > 0 && !grid.getCopyOfHorizontalElement(listIndex - 1, position).equals(GridOption.EMPTY)
                && !grid.getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                && !grid.getCopyOfVerticalElement(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < grid.getHorizontalListSize() - 1
                && !grid.getCopyOfHorizontalElement(listIndex + 1, position).equals(GridOption.EMPTY)
                && !grid.getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                && !grid.getCopyOfVerticalElement(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
            points++;

        }
        return points;
    }

    /**
     * This method calculate the player points after an vertical line is set.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            the line
     * @return the points scored
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    @Override
    public Integer verticalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (listIndex > 0 && !grid.getCopyOfVerticalElement(listIndex - 1, position).equals(GridOption.EMPTY)
                && !grid.getCopyOfHorizontalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                && !grid.getCopyOfHorizontalElement(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < grid.getVerticalListSize() - 1
                && !grid.getCopyOfVerticalElement(listIndex + 1, position).equals(GridOption.EMPTY)
                && !grid.getCopyOfHorizontalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                && !grid.getCopyOfHorizontalElement(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    /**
     * This method calculate the player points after an diagonal line is set.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            the line
     * @return the points scored
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    @Override
    public Integer diagonalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        throw new UnsupportedOperationException();
    }
}
