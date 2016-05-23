package it.unibo.squaresgameteam.squares.model.classes;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.BaseGrid;
import it.unibo.squaresgameteam.squares.model.interfaces.PointsCounterStrategy;
import it.unibo.squaresgameteam.squares.model.interfaces.SquareGrid;

/**
 * This class implements the interface PointsCounterStrategy. It is used to
 * calculate the points in a "triangle game" after a player has made a move.
 */
public class TriangleGridPointsCounterImpl implements PointsCounterStrategy {

    private final BaseGrid grid;

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
        if (listIndex > 0 && !((SquareGrid) grid).getVerticalLinePlayer(position, listIndex - 1).equals(GridOption.EMPTY)
                && !((TriangleGridImpl) grid).getDiagonalLinePlayer(listIndex - 1, position)
                        .equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < ((SquareGrid) grid).getHorizontalListSize() - 1
                && !((SquareGrid) grid).getVerticalLinePlayer(position + 1, listIndex).equals(GridOption.EMPTY)
                && !((TriangleGridImpl) grid).getDiagonalLinePlayer(listIndex, position).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    @Override
    public Integer verticalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (listIndex > 0 && !((SquareGrid) grid).getHorizontalLinePlayer(position, listIndex - 1).equals(GridOption.EMPTY)
                && !((TriangleGridImpl) grid).getDiagonalLinePlayer(position, listIndex - 1)
                        .equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < ((SquareGrid) grid).getVerticalListSize() - 1
                && !((SquareGrid) grid).getHorizontalLinePlayer(position + 1, listIndex).equals(GridOption.EMPTY)
                && !((TriangleGridImpl) grid).getDiagonalLinePlayer(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    @Override
    public Integer diagonalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (!((SquareGrid) grid).getHorizontalLinePlayer(listIndex, position).equals(GridOption.EMPTY)
                && !((SquareGrid) grid).getVerticalLinePlayer(position + 1, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        if (!((SquareGrid) grid).getHorizontalLinePlayer(listIndex + 1, position).equals(GridOption.EMPTY)
                && !((SquareGrid) grid).getVerticalLinePlayer(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }
}
