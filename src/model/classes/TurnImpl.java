package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.LastMove;
import model.interfaces.Turn;

public class TurnImpl implements Turn {

    private boolean matchStarted = false;
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private static final Integer INITIAL_SCORE = 0;
    private BaseGrid grid;
    private List<LastMove> lastMoveList = new ArrayList<>();

    /**
     * 
     */
    TurnImpl(final Integer rowsNumber, final Integer columnNumber) {
        grid = new BaseGridImpl(rowsNumber, columnNumber);
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
                grid.setPlayerTurn(GridOption.PLAYER1);
            } else {
                grid.setPlayerTurn(GridOption.PLAYER2);
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

        if (grid.getCurrentPlayerTurn().equals(GridOption.PLAYER1)) {
            grid.setPlayerTurn(GridOption.PLAYER2);
        } else {
            grid.setPlayerTurn(GridOption.PLAYER1);
        }
    }

    @Override
    public boolean isEnded() {

        if (!isStarted()) {
            throw new IllegalStateException("the match can't be ended if it isn't even started");
        }

        return getPlayerPoints(GridOption.PLAYER1)
                + getPlayerPoints(GridOption.PLAYER2) == (grid.getHorizontalListSize() - 1)
                        * (grid.getVerticallListSize() - 1) ? true : false;
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
        /*
         * if (!list.equals(ListType.HORIZONTAL) &&
         * !list.equals(ListType.VERTICAL)) { throw new
         * IllegalArgumentException("the list selected does not exist"); }
         */

        switch (list) {
        case HORIZONTAL:
            grid.setHorizontalLine(listIndex, position, grid.getCurrentPlayerTurn());
            if (horizontalPointScored(listIndex, position) > 0) {
                addPoints(horizontalPointScored(listIndex, position));
            } else {
                nextTurn();
            }
            break;
        case VERTICAL:
            grid.setVerticalLine(listIndex, position, grid.getCurrentPlayerTurn());
            if (verticalPointScored(listIndex, position) > 0) {
                addPoints(verticalPointScored(listIndex, position));
            } else {
                nextTurn();
            }
            break;
        default:
            throw new IllegalArgumentException("the list selected does not exist");
        }
        lastMoveList.add(grid.getCopyOfLastMove());
        int i = 0;
        for(LastMove last : lastMoveList){
            System.out.println("Elemento " + i + " " + last.getLastListType());
            i++;
        }
        System.out.println();
        /*
         * if (list.equals(ListType.HORIZONTAL)) {
         * grid.setHorizontalLine(listIndex, position,
         * grid.getCurrentPlayerTurn()); if (horizontalPointScored(listIndex,
         * position) > 0) { addPoints(horizontalPointScored(listIndex,
         * position)); } else { nextTurn(); } } else {
         * grid.setVerticalLine(listIndex, position,
         * grid.getCurrentPlayerTurn()); if (verticalPointScored(listIndex,
         * position) > 0) { addPoints(verticalPointScored(listIndex, position));
         * } else { nextTurn(); } }
         */

    }

    private Integer horizontalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex > 0) {
            if (grid.getCopyOfHorizontalElement(listIndex - 1, position) != GridOption.EMPTY) {
                if (grid.getCopyOfVerticalElement(position, listIndex - 1) != GridOption.EMPTY
                        && grid.getCopyOfVerticalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        if (listIndex < grid.getHorizontalListSize() - 1) {
            if (grid.getCopyOfHorizontalElement(listIndex + 1, position) != GridOption.EMPTY) {
                if (grid.getCopyOfVerticalElement(position, listIndex) != GridOption.EMPTY
                        && grid.getCopyOfVerticalElement(position + 1, listIndex) != GridOption.EMPTY) {
                    points++;
                }
            }
        }
        return points;
    }

    private Integer verticalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex > 0) {
            if (grid.getCopyOfVerticalElement(listIndex - 1, position) != GridOption.EMPTY) {
                if (grid.getCopyOfHorizontalElement(position, listIndex - 1) != GridOption.EMPTY
                        && grid.getCopyOfHorizontalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        if (listIndex < grid.getVerticallListSize() - 1) {
            if (grid.getCopyOfVerticalElement(listIndex + 1, position) != GridOption.EMPTY) {
                if (grid.getCopyOfHorizontalElement(position, listIndex) != GridOption.EMPTY
                        && grid.getCopyOfHorizontalElement(position + 1, listIndex) != GridOption.EMPTY) {
                    points++;
                }
            }
        }
        return points;
    }

    private void addPoints(final Integer points) {

        if (!isStarted()) {
            throw new IllegalStateException();
        }

        if (grid.getCurrentPlayerTurn().equals(GridOption.PLAYER1)) {
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

    @Override
    public void undoLastMove() {

        if (lastMoveList.isEmpty()) {
            throw new IllegalStateException("you can't undo if you didn't made a move");
        }

        if (grid.getCopyOfLastMove().getLastListType().equals(ListType.HORIZONTAL)) {
            addPoints(-horizontalPointScored(grid.getCopyOfLastMove().getLastListIndex(),
                    grid.getCopyOfLastMove().getLastPosition()));
            grid.setHorizontalLine(grid.getCopyOfLastMove().getLastListIndex(), grid.getCopyOfLastMove().getLastPosition(),
                    GridOption.EMPTY);
        } else {
            addPoints(
                    -verticalPointScored(grid.getCopyOfLastMove().getLastListIndex(), grid.getCopyOfLastMove().getLastPosition()));
            grid.setVerticalLine(grid.getCopyOfLastMove().getLastListIndex(), grid.getCopyOfLastMove().getLastPosition(),
                    GridOption.EMPTY);
        }

        lastMoveList.remove(grid.getCopyOfLastMove());
        grid.getCopyOfLastMove().setLastListType(lastMoveList.get(lastMoveList.size() - 1).getLastListType());
        grid.getCopyOfLastMove().setLastListIndex(lastMoveList.get(lastMoveList.size() - 1).getLastListIndex());
        grid.getCopyOfLastMove().setLastPosition(lastMoveList.get(lastMoveList.size() - 1).getLastPosition());
    }

    @Override
    public BaseGrid getCopyOfGrid() {
        BaseGrid copyOfThisGrid = this.grid;
        return copyOfThisGrid;
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
