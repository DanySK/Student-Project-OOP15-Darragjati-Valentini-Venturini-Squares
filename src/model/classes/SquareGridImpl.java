package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.exceptions.UnsupportedSizeException;
import model.exceptions.MoveAlreadyDoneException;
import model.exceptions.MoveNotFoundException;
import model.exceptions.UnexistentLineListException;
import model.interfaces.SquareGrid;

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
    private static final String ERROR_MESSAGE = "the list does not exist";

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
    public GridOption getCopyOfHorizontalElement(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        checkCorrectHorizontalInput(listIndex, position);
        final GridOption copyOfHorizontalElement = horizontal.get(listIndex).get(position);
        // CHECKSTYLE:OFF:
        return copyOfHorizontalElement;
        // CHECKSTYLE:ON:
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
     * @return the number of points scored by making a move
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     * @throws IllegalStateEception
     *             if the move has been already made
     */
    protected void setHorizontalLine(final int listIndex, final int position, final GridOption playerTurn)
            throws UnexistentLineListException {
        checkCorrectHorizontalInput(listIndex, position);
        //Integer points = 0;
        if (playerTurn.equals(GridOption.EMPTY)) {
            if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                throw new MoveNotFoundException("You can't undo a move that wasn't made");
            } else {
             //   points = horizontalPointScored(listIndex, position);
                horizontal.get(listIndex).set(position, playerTurn);
            }
        } else {
            if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                horizontal.get(listIndex).set(position, playerTurn);
              //  points = horizontalPointScored(listIndex, position);
            } else {
                throw new MoveAlreadyDoneException("You can't make a move that has been already made");
            }
        }
      //  return points;
    }

    /**
     * This method checks after doing a horizontal move if it has scored a
     * point.
     * 
     * @param listIndex
     *            the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            the index of the chosen list where the player wants to set the
     *            line
     * @return the number of points scored by making a move
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    protected Integer horizontalPointScored(final int listIndex, final int position)
            throws UnexistentLineListException {
        int points = 0;
       // CalculatePlayerPoints calculatePoints = new CalculatePlayerPoints();
        //points = calculatePoints.horizontalPointScored(this.getClass(), listIndex, position);
        /*
         * if (listIndex > 0 && !getCopyOfHorizontalElement(listIndex - 1,
         * position).equals(GridOption.EMPTY) &&
         * !getPreviousTransversalList(ListType.HORIZONTAL, listIndex,
         * position).equals(GridOption.EMPTY) &&
         * !getNextTransversalList(ListType.HORIZONTAL, listIndex,
         * position).equals(GridOption.EMPTY)) { points++; } if (listIndex <
         * horizontal.size() - 1 && !getCopyOfHorizontalElement(listIndex + 1,
         * position).equals(GridOption.EMPTY) &&
         * !getPreviousTransversalList(ListType.HORIZONTAL, listIndex,
         * position).equals(GridOption.EMPTY) &&
         * !getNextTransversalList(ListType.HORIZONTAL, listIndex,
         * position).equals(GridOption.EMPTY)) { points++; }
         */
        return points;
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
    public GridOption getCopyOfVerticalElement(final Integer listIndex, final Integer position)
            throws UnexistentLineListException {
        checkCorrectVerticalInput(listIndex, position);
        final GridOption copyOfVerticalElement = vertical.get(listIndex).get(position);
        // CHECKSTYLE:OFF:
        return copyOfVerticalElement;
        // CHECKSTYLE:ON:
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
     * @return the number of points scored by making a move
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     * @throws IllegalStateEception
     *             if the move has been already made
     */
    protected void setVerticalLine(final int listIndex, final int position, final GridOption playerTurn)
            throws UnexistentLineListException {
        checkCorrectVerticalInput(listIndex, position);
       // Integer points = 0;
        if (playerTurn.equals(GridOption.EMPTY)) {
            if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                throw new MoveNotFoundException("You can't undo a move that wasn't made");
            } else {
               // points = verticalPointScored(listIndex, position);
                vertical.get(listIndex).set(position, playerTurn);
            }
        } else {
            if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
                vertical.get(listIndex).set(position, playerTurn);
               // points = verticalPointScored(listIndex, position);
            } else {
                throw new MoveAlreadyDoneException("You can't make a move that has been already made");
            }
        }
       // return points;
    }

    /**
     * This method checks after doing a vertical move if it has scored a point.
     * 
     * @param listIndex
     *            the number of the vertical list where the player wants to set
     *            a line
     * @param position
     *            the index of the chosen list where the player wants to set a
     *            line
     * @return the number of points scored by making a move
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    protected Integer verticalPointScored(final Integer listIndex, final Integer position) {
        int points = 0;
 //       CalculatePlayerPoints calculatePoints = new CalculatePlayerPoints();
      //  points = calculatePoints.verticalPointScored(this.getClass(), listIndex, position);
       
       /* if (listIndex > 0 && !getPreviousParallelList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)
                && !getPreviousTransversalList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)
                && !getNextTransversalList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)) {
            points++;
        }
        if (listIndex < vertical.size() - 1
                && !getNextParallelList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)
                && !getPreviousTransversalList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)
                && !getNextTransversalList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)) {
            points++;
        }*/
        return points;
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
