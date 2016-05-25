package it.unibo.squaresgameteam.squares.model.classes;

import java.util.ArrayList;
import java.util.List;

import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.UnsupportedSizeException;
import it.unibo.squaresgameteam.squares.model.exceptions.MoveAlreadyDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.MoveNotFoundException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.Move;
import it.unibo.squaresgameteam.squares.model.interfaces.SquareGrid;

/**
 * This class implements the methods of the interface SquereGrid. It is used to
 * create the game's playable field. The class also provides the methods to get
 * the main informations about it like which one of the two players has set a
 * move or how much wide is the playeable field.
 */
public class SquareGridImpl implements SquareGrid {

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

    private void checkCorrectHorizontalInput(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        if (listIndex < 0 || listIndex > horizontal.size()) {
            throw new UnexistentLineListException();
        }
        if (position < 0 || position > horizontal.get(listIndex).size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void setLine(final Move move, final GridOption currentPlayerTurn) throws UnexistentLineListException {
        switch (move.getListType()) {
        case HORIZONTAL:
            setHorizontalLine(move.getListIndex(), move.getPosition(), currentPlayerTurn);
            break;
        case VERTICAL:
            setVerticalLine(move.getListIndex(), move.getPosition(), currentPlayerTurn);
            break;
        default:
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public GridOption getHorizontalLinePlayer(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        checkCorrectHorizontalInput(listIndex, position);
        return horizontal.get(listIndex).get(position).equals(GridOption.EMPTY) ? GridOption.EMPTY
                : horizontal.get(listIndex).get(position).equals(GridOption.PLAYER1) ? GridOption.PLAYER1
                        : GridOption.PLAYER2;
    }

    /**
     * This method makes a player make a horizontal move.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            the line
     * @param playerTurn
     *            which one of the two players is making the move
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     * @throws IllegalStateEception
     *             if the move has been already made
     */
    protected void setHorizontalLine(final int listIndex, final int position, final GridOption playerTurn)
            throws UnexistentLineListException {
        checkCorrectHorizontalInput(listIndex, position);
        if (playerTurn.equals(GridOption.EMPTY)) {
            if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                throw new MoveNotFoundException("You can't undo a move that wasn't made");
            } else {
                horizontal.get(listIndex).set(position, playerTurn);
            }
        } else {
            if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                horizontal.get(listIndex).set(position, playerTurn);
            } else {
                throw new MoveAlreadyDoneException("You can't make a move that has been already made");
            }
        }
    }

    private void checkCorrectVerticalInput(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        if (listIndex < 0 || listIndex > vertical.size()) {
            throw new UnexistentLineListException();
        }
        if (position < 0 || position > vertical.get(listIndex).size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public GridOption getVerticalLinePlayer(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        checkCorrectVerticalInput(listIndex, position);
        return vertical.get(listIndex).get(position).equals(GridOption.EMPTY) ? GridOption.EMPTY
                : vertical.get(listIndex).get(position).equals(GridOption.PLAYER1) ? GridOption.PLAYER1
                        : GridOption.PLAYER2;
    }

    /**
     * This method makes a player make a vertical move.
     * 
     * @param listIndex
     *            is the number of the vertical list where the player wants to
     *            set a line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            a line
     * @param playerTurn
     *            which one of the two players is making the move
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     * @throws IllegalStateEception
     *             if the move has been already made
     */
    protected void setVerticalLine(final int listIndex, final int position, final GridOption playerTurn)
            throws UnexistentLineListException {
        checkCorrectVerticalInput(listIndex, position);
        if (playerTurn.equals(GridOption.EMPTY)) {
            if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                throw new MoveNotFoundException("You can't undo a move that wasn't made");
            } else {
                vertical.get(listIndex).set(position, playerTurn);
            }
        } else {
            if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                vertical.get(listIndex).set(position, playerTurn);
            } else {
                throw new MoveAlreadyDoneException("You can't make a move that has been already made");
            }
        }
    }

    @Override
    public Integer getHorizontalListSize() {
        return horizontal.size();
    }

    @Override
    public Integer getVerticalListSize() {
        return vertical.size();
    }
}
