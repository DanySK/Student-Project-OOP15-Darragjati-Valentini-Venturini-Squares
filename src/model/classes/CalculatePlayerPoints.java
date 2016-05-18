package model.classes;

import model.enumerations.GridOption;
import model.exceptions.UnexistentLineListException;
import model.interfaces.SquareGrid;

/**
 * This class is used to calculate the points after a player has made a move.
 */
public class CalculatePlayerPoints {

    private final SquareGrid grid;

    /**
     * The consctructor takes in input the grid to determinate how to calculate
     * the points.
     * 
     * @param grid
     *            the current grid
     */
    // CHECKSTYLE:OFF:
    public CalculatePlayerPoints(final SquareGrid grid) {
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
    protected Integer horizontalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        int points = 0;
        if (this.grid.getClass().equals(SquareGridImpl.class)) {
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
        }
        if (this.grid.getClass().equals(TriangleGridImpl.class)) {
            if (listIndex > 0 && !grid.getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                    && !((TriangleGridImpl) grid).getCopyOfDiagonalElement(listIndex - 1, position)
                            .equals(GridOption.EMPTY)) {
                points++;
            }
            if (listIndex < grid.getHorizontalListSize() - 1
                    && !grid.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                    && !((TriangleGridImpl) grid).getCopyOfDiagonalElement(listIndex, position)
                            .equals(GridOption.EMPTY)) {
                points++;
            }
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
    protected Integer verticalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        int points = 0;
        if (this.grid.getClass().equals(SquareGridImpl.class)) {
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
        }
        if (this.grid.getClass().equals(TriangleGridImpl.class)) {
            if (listIndex > 0 && !grid.getCopyOfHorizontalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                    && !((TriangleGridImpl) grid).getCopyOfDiagonalElement(position, listIndex - 1)
                            .equals(GridOption.EMPTY)) {
                points++;
            }
            if (listIndex < grid.getVerticalListSize() - 1
                    && !grid.getCopyOfHorizontalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                    && !((TriangleGridImpl) grid).getCopyOfDiagonalElement(position, listIndex)
                            .equals(GridOption.EMPTY)) {
                points++;
            }
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
    protected Integer diagonalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        int points = 0;
        if (!grid.getCopyOfHorizontalElement(listIndex, position).equals(GridOption.EMPTY)
                && !grid.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        if (!grid.getCopyOfHorizontalElement(listIndex + 1, position).equals(GridOption.EMPTY)
                && !grid.getCopyOfVerticalElement(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }
}
