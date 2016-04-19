package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.LastMove;


public class BaseGridImpl implements BaseGrid {

    private List<List<GridOption>> horizontal = new ArrayList<>();
    private List<List<GridOption>> vertical = new ArrayList<>();
    private GridOption turn = GridOption.EMPTY;
    //CHECKSTYLE:OFF:
    LastMove lastMove = new LastMoveImpl();
  //CHECKSTYLE:ON:
    private static final Integer MINIMUM_SIZE = 4;
    private static final Integer MAXIMUM_SIZE = 10;

    public BaseGridImpl(final Integer rowsNumber, final Integer columnNumber) {

        if (rowsNumber < MINIMUM_SIZE || rowsNumber > MAXIMUM_SIZE || columnNumber < MINIMUM_SIZE
                || columnNumber > MAXIMUM_SIZE) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < rowsNumber + 1; i++) {
            horizontal.add(createEmptyGrid(rowsNumber));
        }
        for (int i = 0; i < columnNumber + 1; i++) {
            vertical.add(createEmptyGrid(columnNumber));
        }
    }

    private List<GridOption> createEmptyGrid(final Integer size) {

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

    @Override
    public GridOption getCurrentPlayerTurn() {
        return this.turn;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setPlayerTurn(final GridOption turn) {
        // CHECKSTYLE:ON:
        this.turn = turn;
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
                lastMove.setLastListType(ListType.HORIZONTAL);
                lastMove.setLastListIndex(listIndex);
                lastMove.setLastPosition(position);
            } else {
                throw new IllegalStateException();
            }
        }
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
                lastMove.setLastListType(ListType.VERTICAL);
                lastMove.setLastListIndex(listIndex);
                lastMove.setLastPosition(position);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public LastMove getLastMove() {
        return this.lastMove;
    }
    
    @Override
    public LastMove getCopyOfLastMove() {
        LastMove copyOfLastMove = new LastMoveImpl();
        copyOfLastMove.setLastListType(this.lastMove.getLastListType());
        copyOfLastMove.setLastListIndex(this.lastMove.getLastListIndex());
        copyOfLastMove.setLastPosition(this.lastMove.getLastPosition());
        return copyOfLastMove;
    }

    @Override
    public Integer getHorizontalListSize() {
        return horizontal.size();
    }

    @Override
    public Integer getVerticallListSize() {
        return vertical.size();
    }
    /*
     * private GridOption getPreviousParallelList(final int listIndex, final int
     * position) {
     * 
     * if (listIndex != 0 || listIndex != horizontalLists + 1) {
     * 
     * return getCopyOfElement(listIndex - 1, position); } else { throw new
     * IllegalArgumentException(); } }
     * 
     * private GridOption getNextParallelList(final int listIndex, final int
     * position) {
     * 
     * if (listIndex != 0 || listIndex != horizontalLists + 1) {
     * 
     * return getCopyOfElement(listIndex + 1, position); } else { throw new
     * IllegalArgumentException(); } }
     */
}
