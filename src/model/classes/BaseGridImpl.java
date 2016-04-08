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
    private GridOption turn = GridOption.EMPTY;
    private boolean matchStarted = false;
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private Integer rows;
    private Integer columns;
    private Integer horizontalLists;
    private Integer verticalLists;
    private static final Integer INITIAL_SCORE = 0;
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

        this.rows = rowsNumber;
        this.columns = columnNumber;
        this.horizontalLists = rowsNumber + 1;
        this.verticalLists = columnNumber + 1;

        for (int i = 0; i < horizontalLists; i++) {
            horizontal.add(createEmptyGrid(rowsNumber));
        }
        for (int i = 0; i < verticalLists; i++) {
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
    public void startMatch() {

        if (!isStarted()) {
            this.scorePlayer1 = INITIAL_SCORE;
            this.scorePlayer2 = INITIAL_SCORE;
            randomizeTurn();
            matchStarted = true;
        } else {
            throw new IllegalStateException("Match già iniziato");
        }
    }

    private void randomizeTurn() {

        if (!isStarted()) {
            Random randomTurn = new Random();

            if (randomTurn.nextInt(2) == 0) {
                this.turn = GridOption.PLAYER1;
            } else {
                this.turn = GridOption.PLAYER2;
            }
        } else {
            throw new IllegalStateException();
        }

    }

    @Override
    public boolean isStarted() {
        return this.matchStarted;
    }

    @Override
    public boolean isEnded() {
        return (!isStarted() || (scorePlayer1 + scorePlayer2) < horizontal.get(0).size() * horizontal.get(0).size())
                ? false : true;
    }

    @Override
    public GridOption getCurrentPlayerTurn() {
        if (isStarted()) {
            return this.turn;
        } else {
            throw new IllegalStateException();
        }
    }

    private void nextTurn() {

        if (!isStarted()) {
            throw new IllegalStateException();
        }

        if (getCurrentPlayerTurn().equals(GridOption.PLAYER1)) {
            this.turn = GridOption.PLAYER2;
        } else {
            this.turn = GridOption.PLAYER1;
        }
    }

    @Override
    public Integer getPlayerPoints(final GridOption player) {

        if (!isStarted()) {
            return INITIAL_SCORE;
        }

        if (player.equals(GridOption.PLAYER1) || player.equals(GridOption.PLAYER2)) {
            return (player.equals(GridOption.PLAYER1)) ? scorePlayer1 : scorePlayer2;
        } else {
            throw new IllegalArgumentException();
        }

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

    private boolean checkCorrectVerticalInput(final Integer listIndex, final Integer position) {

        return (listIndex < 0 || listIndex > vertical.size() || position < 0 || position > vertical.get(0).size())
                ? false : true;
    }
    
    @Override
    public GridOption getVerticalElement(final Integer listIndex, final Integer elementIndex) {

        if (!checkCorrectVerticalInput(listIndex, elementIndex)) {
            throw new IllegalArgumentException();
        }

        return vertical.get(listIndex).get(elementIndex);
    }
    
    @Override
    public void setVerticalLine(final int listIndex, final int position) {

        if (!checkCorrectVerticalInput(listIndex, position)) {
            throw new IllegalArgumentException();
        }

        if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
            vertical.get(listIndex).set(position, getCurrentPlayerTurn());

            if (verticalPointScored(listIndex, position).equals(0)) {
                nextTurn();
            }
        } else {
            throw new IllegalStateException();
        }
    }
    
    private Integer verticalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex != 0) {
            if (getVerticalElement(listIndex - 1, position) != GridOption.EMPTY) {
                if (getHorizontalElement(position, listIndex - 1) != GridOption.EMPTY
                        && getHorizontalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        if (listIndex != vertical.get(0).size()) {
            if (getVerticalElement(listIndex + 1, position) != GridOption.EMPTY) {
                if (getHorizontalElement(position, listIndex) != GridOption.EMPTY
                        && getHorizontalElement(position + 1, listIndex) != GridOption.EMPTY) {
                    points++;
                }
            }
        }
        addPoints(points);
        return points;
    }

    private boolean checkCorrectHorizontalInput(final Integer listIndex, final Integer position) {

        return (listIndex < 0 || listIndex > horizontal.size() || position < 0 || position > horizontal.get(0).size())
                ? false : true;
    }

    @Override
    public GridOption getHorizontalElement(final Integer listIndex, final Integer position) {

        if (!checkCorrectHorizontalInput(listIndex, position)) {
            throw new IllegalArgumentException();
        }

        return horizontal.get(listIndex).get(position);
    }

    @Override
    public void setHorizontalLine(final int listIndex, final int position) {

        if (!checkCorrectHorizontalInput(listIndex, position)) {
            throw new IllegalArgumentException();
        }

        if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
            horizontal.get(listIndex).set(position, getCurrentPlayerTurn());

            if (horizontalPointScored(listIndex, position).equals(0)) {
                nextTurn();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    private Integer horizontalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex != 0) {
            if (getHorizontalElement(listIndex - 1, position) != GridOption.EMPTY) {
                if (getVerticalElement(position, listIndex - 1) != GridOption.EMPTY
                        && getVerticalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        if (listIndex != horizontal.get(0).size()) {
            if (getHorizontalElement(listIndex + 1, position) != GridOption.EMPTY) {
                if (getVerticalElement(position, listIndex) != GridOption.EMPTY
                        && getVerticalElement(position + 1, listIndex) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        addPoints(points);
        return points;
    }

    /*
    private void checkCorrectInput(final Integer listIndex, final Integer position) {

        if (listIndex < 0 || listIndex > horizontal.size()) {
            throw new IllegalArgumentException();
        }

        if (position < 0 || position > horizontal.get(listIndex).size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public GridOption getCopyOfElement(final Integer listIndex, final Integer position) {

        checkCorrectInput(listIndex, position);

        GridOption copyOfMove = horizontal.get(listIndex).get(position);

        return copyOfMove;
    }

    @Override
    public void setLine(final int listIndex, final int position) {

        checkCorrectInput(listIndex, position);

        if (getCopyOfElement(listIndex, position).equals(GridOption.EMPTY)) {

            horizontal.get(listIndex).set(position, getCurrentPlayerTurn());

            if (!pointScored(listIndex, position)) {
                nextTurn();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    private boolean pointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex >= 0 && listIndex <= rows) {

            if (listIndex != 0) {
                if (getPreviousParallelList(listIndex, position) != GridOption.EMPTY) {
                    if (getCopyOfElement(listIndex + horizontalLists, listIndex - 1) != GridOption.EMPTY
                            && getCopyOfElement(listIndex + horizontalLists + 1, listIndex - 1) != GridOption.EMPTY) {
                        points++;
                    }
                }
            }

            if (listIndex != rows) {
                if (getNextParallelList(listIndex, position) != GridOption.EMPTY) {
                    if (getCopyOfElement(listIndex + horizontalLists, listIndex) != GridOption.EMPTY
                            && getCopyOfElement(listIndex + horizontalLists + 1, listIndex) != GridOption.EMPTY) {
                        points++;
                    }
                }
            }

            addPoints(points);
            return points > 0 ? true : false;
        }

        if (listIndex > rows && listIndex <= rows + columns + 1) {

            if (listIndex != rows + 1) {
                if (getPreviousParallelList(listIndex, position) != GridOption.EMPTY) {
                    if (getCopyOfElement(position, listIndex - horizontalLists - 1) != GridOption.EMPTY
                            && getCopyOfElement(position + 1, listIndex - horizontalLists - 1) != GridOption.EMPTY) {
                        points++;
                    }
                }
            }

            if (listIndex != rows + columns + 1) {
                if (getNextParallelList(listIndex, position) != GridOption.EMPTY) {
                    if (getCopyOfElement(position, listIndex - horizontalLists) != GridOption.EMPTY
                            && getCopyOfElement(position + 1, listIndex - horizontalLists) != GridOption.EMPTY) {
                        points++;
                    }
                }
            }

            addPoints(points);
            return points > 0 ? true : false;
        }

        throw new IllegalArgumentException();
    }*/

    private void addPoints(final Integer points) {

        if (!isStarted()) {
            throw new IllegalStateException();
        }

        if (getCurrentPlayerTurn().equals(GridOption.PLAYER1)) {
            scorePlayer1 += points;
        } else {
            scorePlayer2 += points;
        }
    }

    private GridOption getPreviousParallelList(final int listIndex, final int position) {

        if (listIndex != 0 || listIndex != horizontalLists + 1) {

            return getCopyOfElement(listIndex - 1, position);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private GridOption getNextParallelList(final int listIndex, final int position) {

        if (listIndex != 0 || listIndex != horizontalLists + 1) {

            return getCopyOfElement(listIndex + 1, position);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public GridOption getWinner() {

        if (isEnded()) {
            if (getPlayerPoints(GridOption.PLAYER1).equals(getPlayerPoints(GridOption.PLAYER2))) {
                return GridOption.EMPTY;
            }

            return (getPlayerPoints(GridOption.PLAYER1) > getPlayerPoints(GridOption.PLAYER2)) ? GridOption.PLAYER1
                    : GridOption.PLAYER2;
        } else {
            throw new IllegalStateException();
        }
    }

}
