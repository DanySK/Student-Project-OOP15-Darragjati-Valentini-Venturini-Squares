package it.unibo.squaresgameteam.squares.model.classes;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.enumerations.ListType;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.Move;
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
    public Integer getPoints(final Move move) throws UnexistentLineListException {
        switch (move.getListType()) {
        case HORIZONTAL:
            horizontalPointScored(move.getListIndex(), move.getPosition());
            break;
        case VERTICAL:
            verticalPointScored(move.getListIndex(), move.getPosition());
            break;
        case DIAGONAL:
            diagonalPointScored(move.getListIndex(), move.getPosition());
            break;
        default:
            throw new IllegalStateException();
        }
        return this.pointsScored;
    }

    private void horizontalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (listIndex > 0
                && !grid.getWhoSetTheLine(new MoveImpl(ListType.VERTICAL, position, listIndex - 1))
                        .equals(GridOption.EMPTY)
                && !grid.getDiagonalLinePlayer(listIndex - 1, position).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < grid.getHorizontalListSize() - 1
                && !grid.getWhoSetTheLine(new MoveImpl(ListType.VERTICAL, position + 1, listIndex))
                        .equals(GridOption.EMPTY)
                && !grid.getDiagonalLinePlayer(listIndex, position).equals(GridOption.EMPTY)) {
            points++;
        }
        this.pointsScored = points;
    }

    private void verticalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (listIndex > 0
                && !grid.getWhoSetTheLine(new MoveImpl(ListType.HORIZONTAL, position, listIndex - 1))
                        .equals(GridOption.EMPTY)
                && !grid.getDiagonalLinePlayer(position, listIndex - 1).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < grid.getVerticalListSize() - 1
                && !grid.getWhoSetTheLine(new MoveImpl(ListType.HORIZONTAL, position + 1, listIndex))
                        .equals(GridOption.EMPTY)
                && !grid.getDiagonalLinePlayer(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        this.pointsScored = points;
    }

    private void diagonalPointScored(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        Integer points = 0;
        if (!grid.getWhoSetTheLine(new MoveImpl(ListType.HORIZONTAL, listIndex, position)).equals(GridOption.EMPTY)
                && !grid.getWhoSetTheLine(new MoveImpl(ListType.VERTICAL, position + 1, listIndex))
                        .equals(GridOption.EMPTY)) {
            points++;
        }
        if (!grid.getWhoSetTheLine(new MoveImpl(ListType.HORIZONTAL, listIndex + 1, position)).equals(GridOption.EMPTY)

                && !grid.getWhoSetTheLine(new MoveImpl(ListType.VERTICAL, position, listIndex))
                        .equals(GridOption.EMPTY)) {
            points++;
        }
        this.pointsScored = points;
    }
}
