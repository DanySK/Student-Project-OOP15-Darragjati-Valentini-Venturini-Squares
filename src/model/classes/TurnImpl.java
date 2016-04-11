package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.enumerations.GridOption;

/**
 * 
 * 
 *
 */
public class TurnImpl {

    private GridOption turn = GridOption.EMPTY;
    private boolean matchStarted = false;
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private static final Integer INITIAL_SCORE = 0;

    /**
     * 
     * @param rowsNumber
     *            a
     * @param columnNumber
     *            a
     */

 //   @Override
    public void startMatch() {

        if (!isStarted()) {
            this.scorePlayer1 = INITIAL_SCORE;
            this.scorePlayer2 = INITIAL_SCORE;
            randomizeTurn();
            matchStarted = true;
        } else {
            throw new IllegalStateException("Match already started");
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

//    @Override
    public boolean isStarted() {
        return this.matchStarted;
    }

//    @Override
    public boolean isEnded() {
        return (!isStarted() || (scorePlayer1 + scorePlayer2) < horizontal.get(0).size() * horizontal.get(0).size())
                ? false : true;
    }

//    @Override
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

//    @Override
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

    private boolean verticalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex != 0) {
            if (getCopyOfVerticalElement(listIndex - 1, position) != GridOption.EMPTY) {
                if (getCopyOfHorizontalElement(position, listIndex - 1) != GridOption.EMPTY
                        && getCopyOfHorizontalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        if (listIndex != vertical.get(0).size()) {
            if (getCopyOfVerticalElement(listIndex + 1, position) != GridOption.EMPTY) {
                if (getCopyOfHorizontalElement(position, listIndex) != GridOption.EMPTY
                        && getCopyOfHorizontalElement(position + 1, listIndex) != GridOption.EMPTY) {
                    points++;
                }
            }
        }
        addPoints(points);
        return points > 0 ? true : false;
    }

    private boolean horizontalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex != 0) {
            if (getCopyOfHorizontalElement(listIndex - 1, position) != GridOption.EMPTY) {
                if (getCopyOfVerticalElement(position, listIndex - 1) != GridOption.EMPTY
                        && getCopyOfVerticalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        if (listIndex != horizontal.get(0).size()) {
            if (getCopyOfHorizontalElement(listIndex + 1, position) != GridOption.EMPTY) {
                if (getCopyOfVerticalElement(position, listIndex) != GridOption.EMPTY
                        && getCopyOfVerticalElement(position + 1, listIndex) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        addPoints(points);
        return points > 0 ? true : false;
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
