package model.classes;

import model.enumerations.GridOption;
import model.exceptions.UnexistentLineListException;
import model.interfaces.PointsCounterStrategy;
import model.interfaces.SquareGrid;

/**
 * This class implements the interface PointsCounterStrategy. It is used to
 * calculate the points in a "triangle game" after a player has made a move.
 */
public class TriangleGridPointsCounterImpl implements PointsCounterStrategy {

    private final SquareGrid grid;

    /**
     * The consctructor takes in input the current playable grid.
     * 
     * @param grid
     *            the current grid
     */
    // CHECKSTYLE:OFF:
    public TriangleGridPointsCounterImpl(final SquareGrid grid) {
        // CHECKSTYLE:ON:
        this.grid = grid;
    }

    @Override
    public Integer horizontalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (listIndex > 0 && !grid.getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                && !((TriangleGridImpl) grid).getCopyOfDiagonalElement(listIndex - 1, position)
                        .equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < grid.getHorizontalListSize() - 1
                && !grid.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                && !((TriangleGridImpl) grid).getCopyOfDiagonalElement(listIndex, position).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    @Override
    public Integer verticalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (listIndex > 0 && !grid.getCopyOfHorizontalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                && !((TriangleGridImpl) grid).getCopyOfDiagonalElement(position, listIndex - 1)
                        .equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < grid.getVerticalListSize() - 1
                && !grid.getCopyOfHorizontalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                && !((TriangleGridImpl) grid).getCopyOfDiagonalElement(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    @Override
    public Integer diagonalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
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
