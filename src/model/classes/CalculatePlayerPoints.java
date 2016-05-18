package model.classes;

import model.enumerations.GridOption;
import model.exceptions.UnexistentLineListException;
import model.interfaces.SquareGrid;

public class CalculatePlayerPoints {

    private SquareGrid grid;

    public CalculatePlayerPoints(SquareGrid grid) {
        this.grid = grid;
    }

    protected Integer horizontalPointScored(Integer listIndex, Integer position) throws UnexistentLineListException {
        int points = 0;
        if (this.grid.getClass().equals(SquareGridImpl.class)) {
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
        }
        if (this.grid.getClass().equals(TriangleGridImpl.class)) {
            if (listIndex > 0 && !grid.getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                    && !((TriangleGridImpl) grid).getCopyOfDiagonalElement(listIndex - 1, position).equals(GridOption.EMPTY)) {
                points++;
            }
            if (listIndex < grid.getHorizontalListSize() - 1
                    && !grid.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                    && !((TriangleGridImpl) grid).getCopyOfDiagonalElement(listIndex, position).equals(GridOption.EMPTY)) {
                points++;
            }
        }
        return points;
    }

    protected Integer verticalPointScored(Integer listIndex, Integer position) throws UnexistentLineListException {
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

    protected Integer diagonalPointScored(Integer listIndex, Integer position) throws UnexistentLineListException {
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
