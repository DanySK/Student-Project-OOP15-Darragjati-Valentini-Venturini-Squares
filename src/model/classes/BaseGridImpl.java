package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;

/**
 * 
 * 
 *
 */
public class BaseGridImpl implements BaseGrid {

    protected List<List<GridOption>> horizontal = new ArrayList<>();
    protected List<List<GridOption>> vertical = new ArrayList<>();
    private GridOption turn = GridOption.EMPTY;
    private ListType lastListMove;
    private Integer lastListIndex;
    private Integer lastPosition;
    private static final Integer MINIMUM_SIZE = 4;
    private static final Integer MAXIMUM_SIZE = 10;

    /**
     * 
     * @param rowsNumber
     *            a
     * @param columnNumber
     *            a
     */
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
    
    public ListType getLastListMove(){
        return this.lastListMove;
    }
    
    public Integer getLastListIndex(){
        return this.lastListIndex;
    }
    
    public Integer getLastListPosition(){
        return this.lastPosition;
    }
    
    public GridOption getCurrentPlayerTurn(){
        
        return this.turn;
    }
    
    public void setPlayerTurn(final GridOption turn){
        this.turn = turn;
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
    public void setVerticalLine(final int listIndex, final int position, GridOption playerTurn) {

        checkCorrectVerticalInput(listIndex, position);

        if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
            
            vertical.get(listIndex).set(position, getCurrentPlayerTurn());
            this.lastListMove = ListType.VERTICAL;
            this.lastListIndex = listIndex;
            this.lastPosition = position;
        } else {
            throw new IllegalStateException();
        }
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
    public void setHorizontalLine(final int listIndex, final int position, GridOption playerTurn) {

        checkCorrectHorizontalInput(listIndex, position);

        if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
            
            horizontal.get(listIndex).set(position, getCurrentPlayerTurn());
            this.lastListMove = ListType.HORIZONTAL;
            this.lastListIndex = listIndex;
            this.lastPosition = position;
        } else {
            throw new IllegalStateException();
        }
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
