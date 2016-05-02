package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;

/**
 * This class implements the methods of the interface BaseGrid. It is used to
 * create the game's playable field.
 */
public class BaseGridImpl implements BaseGrid {

    private List<List<GridOption>> horizontal = new ArrayList<>();
    private List<List<GridOption>> vertical = new ArrayList<>();
    private static final Integer MINIMUM_SIZE = 4;
    private static final Integer MAXIMUM_SIZE = 10;

    /**
     * This constructor creates a new playable grid.
     * 
     * @param rowsNumber
     *            the number of rows of the grid
     * @param columnsNumber
     *            the number of columns of the grid
     */
    public BaseGridImpl(final Integer rowsNumber, final Integer columnsNumber) {

        if (rowsNumber < MINIMUM_SIZE || rowsNumber > MAXIMUM_SIZE || columnsNumber < MINIMUM_SIZE
                || columnsNumber > MAXIMUM_SIZE) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < rowsNumber + 1; i++) {
            horizontal.add(createEmptyList(rowsNumber));
        }
        for (int i = 0; i < columnsNumber + 1; i++) {
            vertical.add(createEmptyList(columnsNumber));
        }
    }

    /**
     * This method creates a list of empty moves.
     * 
     * @param size
     *            the list's size
     * @return the created list
     */
    protected List<GridOption> createEmptyList(final Integer size) {
        List<GridOption> emptyGrid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            emptyGrid.add(GridOption.EMPTY);
        }
        return emptyGrid;
    }

    @Override
    public Integer getTotalMoves() {
        return (horizontal.size() * horizontal.get(0).size()) + (vertical.size() * vertical.get(0).size());
    }

    @Override
    public Integer getRemainingMoves() {

        Integer movesLeft = 0;

        for (List<GridOption> list : horizontal) {
            for (GridOption option : list) {
                if (option.equals(GridOption.EMPTY)) {
                    movesLeft++;
                }
            }
        }

        for (List<GridOption> list : vertical) {
            for (GridOption option : list) {
                if (option.equals(GridOption.EMPTY)) {
                    movesLeft++;
                }
            }
        }
        return movesLeft;
    }

    private void checkCorrectHorizontalInput(final Integer listIndex, final Integer position) {

        if (listIndex < 0 || listIndex > horizontal.size()) {
            throw new IllegalArgumentException();
        }

        if (position < 0 || position > horizontal.get(listIndex).size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public GridOption getCopyOfHorizontalElement(final Integer listIndex, final Integer position) {

        checkCorrectHorizontalInput(listIndex, position);
        GridOption copyOfHorizontalElement = horizontal.get(listIndex).get(position);
        return copyOfHorizontalElement;
    }

    @Override
    public void setHorizontalLine(final int listIndex, final int position, final GridOption playerTurn) {

        checkCorrectHorizontalInput(listIndex, position);

        if (playerTurn.equals(GridOption.EMPTY)) {
            if (!horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                horizontal.get(listIndex).set(position, playerTurn);
            } else {
                throw new IllegalStateException(); // da mettere il perchè //
                                                   // lancia quest'eccezione?
            }
        } else {
            if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                horizontal.get(listIndex).set(position, playerTurn);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public Integer horizontalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex > 0) {
            if (!getCopyOfHorizontalElement(listIndex - 1, position).equals(GridOption.EMPTY)) {
                if (!getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                        && !getCopyOfVerticalElement(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
                    points++;
                }
            }
        }

        if (listIndex < horizontal.size() - 1) {
            if (!getCopyOfHorizontalElement(listIndex + 1, position).equals(GridOption.EMPTY)) {
                if (!getCopyOfVerticalElement(position, listIndex).equals(GridOption.EMPTY)
                        && !getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)) {
                    points++;
                }
            }
        }
        return points;
    }

    private void checkCorrectVerticalInput(final Integer listIndex, final Integer position) {

        if (listIndex < 0 || listIndex > vertical.size()) {
            throw new IllegalArgumentException();
        }

        if (position < 0 || position > vertical.get(listIndex).size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public GridOption getCopyOfVerticalElement(final Integer listIndex, final Integer position) {

        checkCorrectVerticalInput(listIndex, position);
        GridOption copyOfVerticalElement = vertical.get(listIndex).get(position);
        return copyOfVerticalElement;
    }

    @Override
    public void setVerticalLine(final int listIndex, final int position, final GridOption playerTurn) {

        checkCorrectVerticalInput(listIndex, position);

        if (playerTurn.equals(GridOption.EMPTY)) {
            if (!vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                vertical.get(listIndex).set(position, playerTurn);
            } else {
                throw new IllegalStateException(); // da mettere il perchè //
                                                   // lancia quest'eccezione?
            }
        } else {
            if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                vertical.get(listIndex).set(position, playerTurn);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public Integer verticalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex > 0) {
            if (!getPreviousParallelList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)) {
                if (!getCopyOfHorizontalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                        && !getCopyOfHorizontalElement(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
                    points++;
                }
            }
        }

        if (listIndex < vertical.size() - 1) {
            if (!getNextParallelList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)) {
                if (!getCopyOfHorizontalElement(position, listIndex).equals(GridOption.EMPTY)
                        && !getCopyOfHorizontalElement(position + 1, listIndex).equals(GridOption.EMPTY)) {
                    points++;
                }
            }
        }
        return points;
    }

    // da tenere?
    private GridOption getPreviousParallelList(final ListType list, final int listIndex, final int position) {
        if (listIndex > 0) {
            switch (list) {
            case HORIZONTAL:
                return getCopyOfHorizontalElement(listIndex - 1, position);
            case VERTICAL:
                return getCopyOfVerticalElement(listIndex - 1, position);
            default:
                throw new IllegalStateException("the list does not exist");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    // da tenere?
    private GridOption getNextParallelList(final ListType list, final int listIndex, final int position) {
        switch (list) {
        case HORIZONTAL:
            if (listIndex < getHorizontalListSize()) {
                return getCopyOfHorizontalElement(listIndex + 1, position);
            } else {
                throw new IllegalArgumentException();
            }
        case VERTICAL:
            if (listIndex < getVerticallListSize()) {
                return getCopyOfVerticalElement(listIndex + 1, position);
            } else {
                throw new IllegalArgumentException();
            }
        default:
            throw new IllegalStateException("the list does not exist");
        }
    }

    @Override
    public Integer getHorizontalListSize() {
        return horizontal.size();
    }

    @Override
    public Integer getVerticallListSize() {
        return vertical.size();
    }
}
