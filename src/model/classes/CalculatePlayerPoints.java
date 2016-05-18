package model.classes;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.exceptions.UnexistentLineListException;
import model.interfaces.SquareGrid;

public class CalculatePlayerPoints {

    SquareGrid grid;

    public CalculatePlayerPoints(SquareGrid grid) {
        this.grid = grid;
    }

    protected Integer horizontalPointScored(SquareGrid grid, Integer listIndex, Integer position)
            throws UnexistentLineListException {
        int points = 0;
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

    protected Integer verticalPointScored(SquareGrid grid, Integer listIndex, Integer position)
            throws UnexistentLineListException {
        int points = 0;
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

    protected Integer diagonalPointScored(Integer listIndex, Integer position) {
        return null;

    }

    private GridOption getPreviousParallelList(final ListType list, final int listIndex, final int position)
            throws UnexistentLineListException {
        if (listIndex > 0) {
            switch (list) {
            case HORIZONTAL:
                return grid.getCopyOfHorizontalElement(listIndex - 1, position);
            case VERTICAL:
                return grid.getCopyOfVerticalElement(listIndex - 1, position);
            default:
                throw new IllegalStateException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private GridOption getNextParallelList(final ListType list, final int listIndex, final int position)
            throws UnexistentLineListException {
        switch (list) {
        case HORIZONTAL:
            if (listIndex < grid.getHorizontalListSize()) {
                return grid.getCopyOfHorizontalElement(listIndex + 1, position);
            } else {
                throw new IllegalArgumentException();
            }
        case VERTICAL:
            if (listIndex < grid.getVerticalListSize()) {
                return grid.getCopyOfVerticalElement(listIndex + 1, position);
            } else {
                throw new IllegalArgumentException();
            }
        default:
            throw new IllegalStateException();
        }
    }

    private GridOption getPreviousTransversalList(final ListType list, final int listIndex, final int position)
            throws UnexistentLineListException {
        if (listIndex > 0) {
            switch (list) {
            case HORIZONTAL:
                return grid.getCopyOfVerticalElement(position, listIndex - 1);
            case VERTICAL:
                return grid.getCopyOfHorizontalElement(position, listIndex - 1);
            default:
                throw new IllegalStateException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private GridOption getNextTransversalList(final ListType list, final int listIndex, final int position)
            throws UnexistentLineListException {
        switch (list) {
        case HORIZONTAL:
            if (listIndex < grid.getHorizontalListSize()) {
                return grid.getCopyOfVerticalElement(position + 1, listIndex - 1);
            } else {
                throw new IllegalArgumentException();
            }
        case VERTICAL:
            if (listIndex < grid.getVerticalListSize()) {
                return grid.getCopyOfHorizontalElement(position + 1, listIndex - 1);
            } else {
                throw new IllegalArgumentException();
            }
        default:
            throw new IllegalStateException();
        }
    }
}
