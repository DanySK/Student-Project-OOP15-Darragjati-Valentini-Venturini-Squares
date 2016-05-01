package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.enumerations.GridOption;
import model.interfaces.BaseGrid;
import model.interfaces.TriangleGrid;

public class TriangleGridImpl extends BaseGridImpl implements TriangleGrid {

    private List<List<GridOption>> diagonal = new ArrayList<>();

    public TriangleGridImpl(Integer rowsNumber, Integer columnsNumber) {
        super(rowsNumber, columnsNumber);

        for (int i = 0; i < rowsNumber; i++) {
            diagonal.add(createEmptyGrid(columnsNumber));
        }
    }

    @Override
    public Integer getTotalMoves() {
        return super.getTotalMoves() + (diagonal.size() * diagonal.get(0).size());
    }

    @Override
    public Integer getRemainingMoves() {

        Integer movesLeft = 0;

        for (List<GridOption> list : diagonal) {
            for (GridOption option : list) {
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
        if (listIndex < super.horizontal.size() - 1) {
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
        if (listIndex < super.vertical.size() - 1) {
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

    public Integer diagonalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (!super.getCopyOfHorizontalElement(listIndex, position).equals(GridOption.EMPTY)
                && super.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }

        if (!super.getCopyOfHorizontalElement(listIndex + 1, position).equals(GridOption.EMPTY)
                && super.getCopyOfVerticalElement(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    @Override
    public Integer getDiagonalListSize() {
        return diagonal.size();
    }
}
