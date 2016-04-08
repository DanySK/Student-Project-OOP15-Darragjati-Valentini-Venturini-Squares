package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.enumerations.GridOption;
import model.interfaces.BaseGrid;


/**
 * 
 * 
 *
 */
public class BaseGridImpl implements BaseGrid {

    private List<List<GridOption>> horizontal = new ArrayList<>();
    private List<List<GridOption>> vertical = new ArrayList<>();
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
    public void setVerticalLine(final int listIndex, final int position) {

        checkCorrectVerticalInput(listIndex, position);

        if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
            vertical.get(listIndex).set(position, getCurrentPlayerTurn());

            verticalPointScored(listIndex, position);
        } else {
            throw new IllegalStateException();
        }
    }
    
    private Integer verticalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex != 0) {
            if (getCopyOfVerticalElement(listIndex - 1, position) != GridOption.EMPTY) {
                if (getHorizontalElement(position, listIndex - 1) != GridOption.EMPTY
                        && getHorizontalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        if (listIndex != vertical.get(0).size()) {
            if (getCopyOfVerticalElement(listIndex + 1, position) != GridOption.EMPTY) {
                if (getHorizontalElement(position, listIndex) != GridOption.EMPTY
                        && getHorizontalElement(position + 1, listIndex) != GridOption.EMPTY) {
                    points++;
                }
            }
        }
        return points;
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
    public void setHorizontalLine(final int listIndex, final int position) {

        checkCorrectHorizontalInput(listIndex, position);

        if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
            horizontal.get(listIndex).set(position, getCurrentPlayerTurn());

            horizontalPointScored(listIndex, position);
            
        } else {
            throw new IllegalStateException();
        }
    }
}
