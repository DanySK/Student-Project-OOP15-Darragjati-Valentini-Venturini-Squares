package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.exceptions.UnsupportedSizeException;
import model.exceptions.UnexistentLineListException;
import model.interfaces.SquareGrid;

/**
 * This class implements the methods of the interface BaseGrid. It is used to
 * create the game's playable field.
 */
public class SquareGridImpl implements SquareGrid {

    private final List<List<GridOption>> horizontal = new ArrayList<>();
    private final List<List<GridOption>> vertical = new ArrayList<>();
    private static final Integer MINIMUM_SIZE = 4;
    private static final Integer MAXIMUM_SIZE = 10;
    private static final String ERROR_MESSAGE = "the list does not exist";

    /**
     * This constructor creates a new playable grid.
     * 
     * @param rowsNumber the number of rows of the grid
     * @param columnsNumber the number of columns of the grid
     * @throws UnsupportedSizeException if the rowsNumber or the coulumnsNumber aren't correct
     */
    public SquareGridImpl(final Integer rowsNumber, final Integer columnsNumber) throws UnsupportedSizeException {
        if (rowsNumber < MINIMUM_SIZE || rowsNumber > MAXIMUM_SIZE || columnsNumber < MINIMUM_SIZE
                || columnsNumber > MAXIMUM_SIZE) {
            throw new UnsupportedSizeException();
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
     * @param size the list's size
     * @return the created list
     */
    protected List<GridOption> createEmptyList(final Integer size) {
        final List<GridOption> emptyGrid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            emptyGrid.add(GridOption.EMPTY);
        }
        return emptyGrid;
    }

    @Override
    public Integer getTotalMoves() {
        return horizontal.size() * horizontal.get(0).size() + vertical.size() * vertical.get(0).size();
    }

    @Override
    public Integer getRemainingMoves() {
        Integer movesLeft = 0;
        for (final List<GridOption> list : horizontal) {
            for (final GridOption option : list) {
                if (option.equals(GridOption.EMPTY)) {
                    movesLeft++;
                }
            }
        }
        for (final List<GridOption> list : vertical) {
            for (final GridOption option : list) {
                if (option.equals(GridOption.EMPTY)) {
                    movesLeft++;
                }
            }
        }
        return movesLeft;
    }

    private void checkCorrectHorizontalInput(final Integer listIndex, final Integer position) throws UnexistentLineListException {
        if (listIndex < 0 || listIndex > horizontal.size()) {
            throw new UnexistentLineListException();
        }
        if (position < 0 || position > horizontal.get(listIndex).size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public GridOption getCopyOfHorizontalElement(final Integer listIndex, final Integer position) throws UnexistentLineListException {
        checkCorrectHorizontalInput(listIndex, position);
        final GridOption copyOfHorizontalElement = horizontal.get(listIndex).get(position);
        // CHECKSTYLE:OFF:
        return copyOfHorizontalElement;
        // CHECKSTYLE:ON:
    }

    @Override
    public Integer setHorizontalLine(final int listIndex, final int position, final GridOption playerTurn) throws UnexistentLineListException {
        checkCorrectHorizontalInput(listIndex, position);
        Integer points = 0;
        if (playerTurn.equals(GridOption.EMPTY)) {
            if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                throw new IllegalStateException("You can't undo a move that wasn't made");
            } else {
                points = horizontalPointScored(listIndex, position);
                horizontal.get(listIndex).set(position, playerTurn);
            }
        } else {
            if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                horizontal.get(listIndex).set(position, playerTurn);
                points = horizontalPointScored(listIndex, position);
            } else {
                throw new IllegalStateException("You can't make a move that has been already made");//da creare una nuova eccezione
            }
        }
        return points;
    }

    /**
     * This method checks after doing a horizontal move if it has scored a
     * point.
     * 
     * @param listIndex the number of the horizontal list where the player wants to set his line
     * @param position the index of the chosen list where the player wants to set the line
     * @return the number of points scored by making a move
     * @throws UnexistentLineListException 
     */
    protected Integer horizontalPointScored(final int listIndex, final int position) throws UnexistentLineListException {
        int points = 0;
        if (listIndex > 0 && !getCopyOfHorizontalElement(listIndex - 1, position).equals(GridOption.EMPTY)
                && !getPreviousTransversalList(ListType.HORIZONTAL, listIndex, position).equals(GridOption.EMPTY)
                && !getNextTransversalList(ListType.HORIZONTAL, listIndex, position).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < horizontal.size() - 1
                && !getCopyOfHorizontalElement(listIndex + 1, position).equals(GridOption.EMPTY)
                && !getPreviousTransversalList(ListType.HORIZONTAL, listIndex, position).equals(GridOption.EMPTY)
                && !getNextTransversalList(ListType.HORIZONTAL, listIndex, position).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    private void checkCorrectVerticalInput(final Integer listIndex, final Integer position) throws UnexistentLineListException {
        if (listIndex < 0 || listIndex > vertical.size()) {
            throw new UnexistentLineListException();
        }
        if (position < 0 || position > vertical.get(listIndex).size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public GridOption getCopyOfVerticalElement(final Integer listIndex, final Integer position) throws UnexistentLineListException {
        checkCorrectVerticalInput(listIndex, position);
        final GridOption copyOfVerticalElement = vertical.get(listIndex).get(position);
        // CHECKSTYLE:OFF:
        return copyOfVerticalElement;
        // CHECKSTYLE:ON:
    }

    @Override
    public Integer setVerticalLine(final int listIndex, final int position, final GridOption playerTurn) throws UnexistentLineListException {
        checkCorrectVerticalInput(listIndex, position);
        Integer points = 0;
        if (playerTurn.equals(GridOption.EMPTY)) {
            if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                throw new IllegalStateException("You can't undo a move that wasn't made");
            } else {
                points = verticalPointScored(listIndex, position);
                vertical.get(listIndex).set(position, playerTurn);
            }
        } else {
            if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                vertical.get(listIndex).set(position, playerTurn);
                points = verticalPointScored(listIndex, position);
            } else {
                throw new IllegalStateException("You can't make a move that has been already made");
            }
        }
        return points;
    }

    /**
     * This method checks after doing a vertical move if it has scored a point.
     * 
     * @param listIndex the number of the vertical list where the player wants to set his line
     * @param position the index of the chosen list where the player wants to set the line
     * @return the number of points scored by making a move
     * @throws UnexistentLineListException if the listIndex input is not correct
     */
    protected Integer verticalPointScored(final int listIndex, final int position) throws UnexistentLineListException {
        int points = 0;
        if (listIndex > 0 && !getPreviousParallelList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)
                && !getPreviousTransversalList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)
                && !getNextTransversalList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < vertical.size() - 1
                && !getNextParallelList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)
                && !getPreviousTransversalList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)
                && !getNextTransversalList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    private GridOption getPreviousParallelList(final ListType list, final int listIndex, final int position) throws UnexistentLineListException {
        if (listIndex > 0) {
            switch (list) {
            case HORIZONTAL:
                return getCopyOfHorizontalElement(listIndex - 1, position);
            case VERTICAL:
                return getCopyOfVerticalElement(listIndex - 1, position);
            default:
                throw new IllegalStateException(ERROR_MESSAGE);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private GridOption getNextParallelList(final ListType list, final int listIndex, final int position) throws UnexistentLineListException {
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
            throw new IllegalStateException(ERROR_MESSAGE);
        }
    }

    private GridOption getPreviousTransversalList(final ListType list, final int listIndex, final int position) throws UnexistentLineListException {
        if (listIndex > 0) {
            switch (list) {
            case HORIZONTAL:
                return getCopyOfVerticalElement(position, listIndex - 1);
            case VERTICAL:
                return getCopyOfHorizontalElement(position, listIndex - 1);
            default:
                throw new IllegalStateException(ERROR_MESSAGE);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private GridOption getNextTransversalList(final ListType list, final int listIndex, final int position) throws UnexistentLineListException {
        switch (list) {
        case HORIZONTAL:
            if (listIndex < getHorizontalListSize()) {
                return getCopyOfVerticalElement(position + 1, listIndex - 1);
            } else {
                throw new IllegalArgumentException();
            }
        case VERTICAL:
            if (listIndex < getVerticallListSize()) {
                return getCopyOfHorizontalElement(position + 1, listIndex - 1);
            } else {
                throw new IllegalArgumentException();
            }
        default:
            throw new IllegalStateException(ERROR_MESSAGE);
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
