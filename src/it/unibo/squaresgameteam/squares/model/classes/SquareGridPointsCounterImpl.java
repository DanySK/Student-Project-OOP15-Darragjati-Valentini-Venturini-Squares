package it.unibo.squaresgameteam.squares.model.classes;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.PointsCounterStrategy;
import it.unibo.squaresgameteam.squares.model.interfaces.SquareGrid;

/**
 * This class implements the interface PointsCounterStrategy. It is used to
 * calculate the points in a "square game" after a player has made a move.
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

    @Override
    public Integer diagonalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        throw new UnsupportedOperationException();
    }
}
