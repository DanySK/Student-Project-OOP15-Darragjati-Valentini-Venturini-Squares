package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.interfaces.BaseGrid;
import model.enumerations.*;

/**
 * 
 * 
 *
 */
public class BaseGridImpl implements BaseGrid {

    private List<List<GridOption>> grid = new ArrayList<>();
    private GridOption turn = GridOption.EMPTY;
    private boolean matchStarted = false;
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private Integer rows;
    private Integer columns;
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

        for (int i = 0; i < rowsNumber + 1; i++) {

            grid.add(createEmptyGrid(rowsNumber));
        }
        for (int i = 0; i < columnNumber + 1; i++) {

            grid.add(createEmptyGrid(columnNumber));
        }

        System.out.print("Oriz: ");
        for (int i = 0; i < rowsNumber + 1; i++) {

            System.out.print(grid.get(i) + " ");
        }
        System.out.print("\nVert: ");
        for (int i = 0; i < columnNumber + 1; i++) {

            System.out.print(grid.get(i) + " ");
        }
        System.out.println();
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

        /*
         * if(!isStarted()){ return false; } if(scorePlayer1 + scorePlayer2 <
         * horizontal.get(0).size() * horizontal.get(0).size()){ return false; }
         * return true;
         */
        return (!isStarted() || (scorePlayer1 + scorePlayer2) < grid.get(0)
                .size() /* horizontal.get(0).size() */) ? false : true;
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
            System.out.println("P1: " + scorePlayer1 + " P2: " + scorePlayer2);
            return (player.equals(GridOption.PLAYER1)) ? scorePlayer1 : scorePlayer2;
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Integer getTotalMoves() {

        //System.out.println((((rows + 1) * grid.get(0).size()) + ((columns + 1) * grid.get(rows + 1 + 1).size())));
        return (((rows + 1) * grid.get(0).size()) + ((columns + 1) * grid.get(rows + 1 + 1).size()));
    }

    @Override
    public Integer getRemainingMoves() {

        Integer movesLeft = 0;

        for (List<GridOption> list : grid) {
            for (GridOption option : list) {
                if (option.equals(GridOption.EMPTY)) {
                    movesLeft++;
                }
            }
        }
        
        return movesLeft;
    }

    private boolean checkCorrectInput(final Integer listIndex, final Integer position) {

        return (listIndex < 0 || listIndex > grid.size() || position < 0 || position > grid.get(listIndex).size())
                ? false : true;
    }

    @Override
    public GridOption getCopyOfElement(final Integer listIndex, final Integer elementIndex) {

        if (!checkCorrectInput(listIndex, elementIndex)) {
            throw new IllegalArgumentException();
        }

        GridOption copyOfMove = grid.get(listIndex).get(elementIndex);

        return copyOfMove;
    }

    @Override
    public void setLine(final int listIndex, final int position) {

        if (!checkCorrectInput(listIndex, position)) {
            throw new IllegalArgumentException();
        }

        if (getCopyOfElement(listIndex, position).equals(GridOption.EMPTY)) {

            grid.get(listIndex).set(position, getCurrentPlayerTurn());

            if (!pointScored(listIndex, position)) {
                nextTurn();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    private boolean pointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex >= 0 && listIndex <= rows) {// da sistemare

            if (listIndex != 0) {
                if (getCopyOfElement(listIndex - 1, position) != GridOption.EMPTY) {
                    if (getCopyOfElement(listIndex + rows, listIndex - 1) != GridOption.EMPTY
                            && getCopyOfElement(listIndex + rows + 1, listIndex - 1) != GridOption.EMPTY) {
                        points++;
                    }
                }
            }

            if (listIndex != rows) {
                if (getCopyOfElement(listIndex + 1, position) != GridOption.EMPTY) {
                    if (getCopyOfElement(listIndex + rows, listIndex) != GridOption.EMPTY
                            && getCopyOfElement(listIndex + rows + 1, listIndex) != GridOption.EMPTY) {
                        points++;
                    }
                }
            }

            addPoints(points);
            return points > 0 ? true : false;
        }

        if (listIndex > rows && listIndex <= rows + columns) {// da sistemare

            if (listIndex != rows + 1) {
                if (getCopyOfElement(listIndex - 1, position) != GridOption.EMPTY) {
                    if (getCopyOfElement(position, listIndex - (rows + 1 + 1)) != GridOption.EMPTY
                            && getCopyOfElement(position + 1, listIndex - (rows + 1 + 1)) != GridOption.EMPTY) {
                        points++;
                    }
                }
            }

            if (listIndex != rows + columns) {
                if (getCopyOfElement(listIndex + 1, position) != GridOption.EMPTY) {
                    if (getCopyOfElement(position, listIndex - (rows + 1)) != GridOption.EMPTY
                            && getCopyOfElement(position + 1, listIndex - (rows + 1)) != GridOption.EMPTY) {
                        points++;
                    }
                }
            }

            addPoints(points);
            return points > 0 ? true : false;
        }

        throw new IllegalArgumentException();
    }

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
