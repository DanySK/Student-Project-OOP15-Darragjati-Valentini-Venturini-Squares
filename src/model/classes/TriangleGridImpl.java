package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.enumerations.GridOption;
import model.interfaces.TriangleGrid;

/**
 * This class modifies the base rules of the game adding a new way to set a move
 * to score a point.
 */
public class TriangleGridImpl extends BaseGridImpl implements TriangleGrid {

    private final List<List<GridOption>> diagonal = new ArrayList<>();

    /**
     * This constructor creates a new playable grid.
     * 
     * @param rowsNumber
     *            the number of rows of the grid
     * @param columnsNumber
     *            the number of columns of the grid
     */
    public TriangleGridImpl(final Integer rowsNumber, final Integer columnsNumber) {
        super(rowsNumber, columnsNumber);

        for (int i = 0; i < rowsNumber; i++) {
            diagonal.add(super.createEmptyList(columnsNumber));
        }
    }

    @Override
    public Integer getTotalMoves() {
        return super.getTotalMoves() + (diagonal.size() * diagonal.get(0).size());
    }

    @Override
    public Integer getRemainingMoves() {

        Integer movesLeft = 0;

        for (final List<GridOption> list : diagonal) {
            for (final GridOption option : list) {
                if (option.equals(GridOption.EMPTY)) {
                    movesLeft++;
                }
            }
        }
        return super.getRemainingMoves() + movesLeft;
    }

    @Override
    public Integer horizontalPointScored(final int listIndex, final int position) {
        int points = 0;
        if (listIndex > 0) {
            if (!super.getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                    && !getCopyOfDiagonalElement(listIndex - 1, position).equals(GridOption.EMPTY)) {
                points++;
            }
        }
        if (listIndex < super.getHorizontalListSize() - 1) {
            if (!super.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                    && !getCopyOfDiagonalElement(listIndex, position).equals(GridOption.EMPTY)) {
                points++;
            }
        }
        return points;
    }

    @Override
    public Integer verticalPointScored(final int listIndex, final int position) {
        int points = 0;
        if (listIndex > 0) {
            if (!super.getCopyOfHorizontalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                    && !getCopyOfDiagonalElement(position, listIndex - 1).equals(GridOption.EMPTY)) {
                points++;
            }
        }
        if (listIndex < super.getVerticallListSize() - 1) {
            if (!super.getCopyOfHorizontalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                    && !getCopyOfDiagonalElement(position, listIndex).equals(GridOption.EMPTY)) {
                points++;
            }
        }
        return points;
    }

    private void checkCorrectDiagonalInput(final Integer listIndex, final Integer position) {
        if (listIndex < 0 || listIndex > diagonal.size()) {
            throw new IllegalArgumentException();
        }
        if (position < 0 || position > diagonal.get(listIndex).size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public GridOption getCopyOfDiagonalElement(final int listIndex, final int position) {
        checkCorrectDiagonalInput(listIndex, position);
        GridOption copyOfDiagonalElement = diagonal.get(listIndex).get(position);
        return copyOfDiagonalElement;
    }

    @Override
    public void setDiagonalLine(final int listIndex, final int position, final GridOption playerTurn) {
        checkCorrectDiagonalInput(listIndex, position);
        if (playerTurn.equals(GridOption.EMPTY)) {
            if (!diagonal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                diagonal.get(listIndex).set(position, playerTurn);
            } else {
                throw new IllegalStateException(); // da mettere il perchè //
                                                   // lancia quest'eccezione?
            }
        } else {
            if (diagonal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                diagonal.get(listIndex).set(position, playerTurn);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public Integer diagonalPointScored(final int listIndex, final int position) {
        int points = 0;
        if (!super.getCopyOfHorizontalElement(listIndex, position).equals(GridOption.EMPTY)
                && !super.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }

        if (!super.getCopyOfHorizontalElement(listIndex + 1, position).equals(GridOption.EMPTY)
                && !super.getCopyOfVerticalElement(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    @Override
    public Integer getDiagonalListSize() {
        return diagonal.size();
    }
}
