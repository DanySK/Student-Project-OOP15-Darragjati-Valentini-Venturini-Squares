package it.unibo.squaresgameteam.squares.model.classes;

import java.util.ArrayList;
import java.util.List;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.UnsupportedSizeException;
import it.unibo.squaresgameteam.squares.model.exceptions.MoveAlreadyDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.MoveNotFoundException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.BaseGrid;
import it.unibo.squaresgameteam.squares.model.interfaces.Move;

/**
 * This class implements the methods of the interface BaseGrid. It is used to
 * create a playable field of the Square game mode. The class also provides the
 * methods to get the main informations about it like which one of the two
 * players has set a move or how much wide is the playeable field.
 */
public class SquareGridImpl implements BaseGrid {

    private final List<List<GridOption>> horizontal = new ArrayList<>();
    private final List<List<GridOption>> vertical = new ArrayList<>();
    private static final Integer MINIMUM_SIZE = 4;
    private static final Integer MAXIMUM_SIZE = 10;

    /**
     * This constructor creates a new playable grid.
     * 
     * @param rowsNumber
     *            the number of rows of the grid
     * @param columnsNumber
     *            the number of columns of the grid
     * @throws UnsupportedSizeException
     *             if the rowsNumber or the coulumnsNumber aren't correct
     */
    public SquareGridImpl(final Integer rowsNumber, final Integer columnsNumber) throws UnsupportedSizeException {
        if (rowsNumber < MINIMUM_SIZE || rowsNumber > MAXIMUM_SIZE || columnsNumber < MINIMUM_SIZE
                || columnsNumber > MAXIMUM_SIZE) {
            throw new UnsupportedSizeException();
        }
        for (int i = 0; i < rowsNumber + 1; i++) {
            horizontal.add(createEmptyList(columnsNumber));
        }
        for (int i = 0; i < columnsNumber + 1; i++) {
            vertical.add(createEmptyList(rowsNumber));
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

    @Override
    public Integer getMatchMaximumPoints() {
        return (getHorizontalListSize() - 1) * (getVerticalListSize() - 1);
    }

    private void checkCorrectInput(final List<List<GridOption>> list, final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        if (listIndex < 0 || listIndex > list.size()) {
            throw new UnexistentLineListException();
        }
        if (position < 0 || position > list.get(listIndex).size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public GridOption getWhoSetTheLine(final Move move) throws UnexistentLineListException {
        List<List<GridOption>> list;
        switch (move.getListType()) {
        case HORIZONTAL:
            list = horizontal;
            break;
        case VERTICAL:
            list = vertical;
            break;
        default:
            throw new UnsupportedOperationException();
        }
        checkCorrectInput(list, move.getListIndex(), move.getPosition());
        return list.get(move.getListIndex()).get(move.getPosition());
    }

    @Override
    public void setLine(final Move move, final GridOption currentPlayerTurn) throws UnexistentLineListException {
        List<List<GridOption>> list;
        switch (move.getListType()) {
        case HORIZONTAL:
            list = horizontal;
            break;
        case VERTICAL:
            list = vertical;
            break;
        default:
            throw new UnsupportedOperationException();
        }
        final Integer listIndex = move.getListIndex();
        final Integer position = move.getPosition();
        checkCorrectInput(list, listIndex, position);
        if (currentPlayerTurn.equals(GridOption.EMPTY)) {
            if (list.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                throw new MoveNotFoundException("You can't undo a move that wasn't made");
            } else {
                list.get(listIndex).set(position, currentPlayerTurn);
            }
        } else {
            if (list.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                list.get(listIndex).set(position, currentPlayerTurn);
            } else {
                throw new MoveAlreadyDoneException("You can't make a move that has been already made");
            }
        }
    }

    /**
     * @return the number of horizontal lists that form the grid
     */
    protected Integer getHorizontalListSize() {
        return horizontal.size();
    }

    /**
     * @return the number of horizontal lists that form the grid
     */
    protected Integer getVerticalListSize() {
        return vertical.size();
    }
}
