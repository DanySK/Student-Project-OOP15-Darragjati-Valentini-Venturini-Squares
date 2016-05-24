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
    private Integer points;
    
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
        // TODO Auto-generated method stub
        return this.points;
    }
 
    protected Integer horizontalPointScored(final Integer listIndex, final Integer position)
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
        this.points = points;
        return points;
    }

    
    protected Integer verticalPointScored(final Integer listIndex, final Integer position)
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
        this.points = points;
        return points;
    }

   
    protected Integer diagonalPointScored(final Integer listIndex, final Integer position)
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
        this.points = points;
        return points;
    }
}
