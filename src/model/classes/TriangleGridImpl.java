package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.enumerations.GridOption;
import model.exceptions.UnsupportedSizeException;
import model.exceptions.MoveAlreadyDoneException;
import model.exceptions.MoveNotFoundException;
import model.exceptions.UnexistentLineListException;
import model.interfaces.TriangleGrid;

/**
 * This class modifies the base rules of the game adding a new way to set a move
 * to score a point.
 */
public class TriangleGridImpl extends SquareGridImpl implements TriangleGrid {

    private final List<List<GridOption>> diagonal = new ArrayList<>();

    /**
     * This constructor creates a new playable grid.
     * 
     * @param rowsNumber
     *            the number of rows of the grid
     * @param columnsNumber
     *            the number of columns of the grid
     * @throws UnsupportedSizeException
     *             if the rowsNumber or columnsNumber aren't correct
     */
    public TriangleGridImpl(final Integer rowsNumber, final Integer columnsNumber) throws UnsupportedSizeException {
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


    protected Integer horizontalPointScored(final int listIndex, final int position)
            throws UnexistentLineListException {
        int points = 0;
        if (listIndex > 0 && !super.getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                && !getCopyOfDiagonalElement(listIndex - 1, position).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < super.getHorizontalListSize() - 1
                && !super.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                && !getCopyOfDiagonalElement(listIndex, position).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    protected Integer verticalPointScored(final int listIndex, final int position) throws UnexistentLineListException {
        int points = 0;
        if (listIndex > 0 && !super.getCopyOfHorizontalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                && !getCopyOfDiagonalElement(position, listIndex - 1).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < super.getVerticalListSize() - 1
                && !super.getCopyOfHorizontalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                && !getCopyOfDiagonalElement(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    private void checkCorrectDiagonalInput(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        if (listIndex < 0 || listIndex > diagonal.size()) {
            throw new UnexistentLineListException();
        }
        if (position < 0 || position > diagonal.get(listIndex).size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public GridOption getCopyOfDiagonalElement(final int listIndex, final int position)
            throws UnexistentLineListException {
        checkCorrectDiagonalInput(listIndex, position);
        final GridOption copyOfDiagonalElement = diagonal.get(listIndex).get(position);
        // CHECKSTYLE:OFF:
        return copyOfDiagonalElement;
        // CHECKSTYLE:ON:
    }

    /**
     * @param listIndex
     *            is the number of the diagonal list where the player wants to
     *            set his line
     * @param position
     *            is the position of the chosen list where the player wants to
     *            set the line
     * @param playerTurn
     *            which one of the two players is making the move
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     * @throws IllegalStateEception
     *             if the move has been already made
     */
    protected void setDiagonalLine(final int listIndex, final int position, final GridOption playerTurn)
            throws UnexistentLineListException {
        checkCorrectDiagonalInput(listIndex, position);
        if (playerTurn.equals(GridOption.EMPTY)) {
            if (diagonal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                throw new MoveNotFoundException("You can't undo a move that wasn't made");
            } else {
                diagonal.get(listIndex).set(position, playerTurn);
            }
        } else {
            if (diagonal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                diagonal.get(listIndex).set(position, playerTurn);
            } else {
                throw new MoveAlreadyDoneException("You can't make a move that has been already made");
            }
        }
    }

    @Override
    public Integer getDiagonalListSize() {
        return diagonal.size();
    }
}
