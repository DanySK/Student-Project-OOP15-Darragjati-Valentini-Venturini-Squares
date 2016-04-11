package model.classes;

import java.util.Random;
import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.Turn;

/**
 * 
 * 
 *
 */
public class TurnImpl extends BaseGridImpl implements Turn {

    private boolean matchStarted = false;
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private static final Integer INITIAL_SCORE = 0;

    /**
     * 
     * @param rowsNumber
     * @param columnNumber
     */
    TurnImpl(final Integer rowsNumber, final Integer columnNumber) {
        super(rowsNumber, columnNumber);
    }

    @Override
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
                this.setPlayerTurn(GridOption.PLAYER1);
            } else {
                this.setPlayerTurn(GridOption.PLAYER2);
            }
        } else {
            throw new IllegalStateException();
        }

    }

    @Override
    public boolean isStarted() {
        return this.matchStarted;
    }

    private void nextTurn() {

        if (!isStarted()) {
            throw new IllegalStateException();
        }

        if (this.getCurrentPlayerTurn().equals(GridOption.PLAYER1)) {
            this.setPlayerTurn(GridOption.PLAYER2);
        } else {
            this.setPlayerTurn(GridOption.PLAYER1);
        }
    }

    @Override
    public boolean isEnded() {

        if (!isStarted()) {
            throw new IllegalStateException("the match can't be ended if it isn't even started");
        }

        return getPlayerPoints(GridOption.PLAYER1) + getPlayerPoints(GridOption.PLAYER2) == (horizontal.size() - 1)
                * (vertical.size() - 1) ? true : false;
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
    public void setLine(final ListType list, final Integer listIndex, final Integer position) {

        if (!list.equals(ListType.HORIZONTAL) || !list.equals(ListType.HORIZONTAL)) {
            throw new IllegalArgumentException("the list selected does not exist");
        }

        if (list.equals(ListType.HORIZONTAL)) {
            this.setHorizontalLine(listIndex, position);
            if (!horizontalPointScored(listIndex, position)) {
                nextTurn();
            }
        } else {
            this.setVerticalLine(listIndex, position);
            if (!verticalPointScored(listIndex, position)) {
                nextTurn();
            }
        }
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

        if (listIndex != horizontal.size()) {
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

        if (listIndex != vertical.size()) {
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

    private void addPoints(final Integer points) {

        if (!isStarted()) {
            throw new IllegalStateException();
        }

        if (this.getCurrentPlayerTurn().equals(GridOption.PLAYER1)) {
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
    
    public void undoLastMove(){
        
        if(getLastListMove().equals(ListType.HORIZONTAL)){
            setHorizontalLine(getLastListIndex(), getLastListPosition(), GridOption.EMPTY);
        } else {
            setVerticalLine(getLastListIndex(), getLastListPosition(), GridOption.EMPTY);
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
