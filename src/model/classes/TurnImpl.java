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

    protected BaseGrid grid;
    private boolean matchStarted = false;
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private static final Integer INITIAL_SCORE = 0;
    private GridOption turn = GridOption.EMPTY;
    private List<LastMove> lastMoveList = new ArrayList<>();

    public TurnImpl(final Integer rowsNumber, final Integer columnNumber) {
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

    private void nextTurn() {

        if (!isStarted()) {
            throw new IllegalStateException();
        }

        if (this.turn.equals(GridOption.PLAYER1)) {
            this.turn = GridOption.PLAYER2;
        } else {
            this.turn = GridOption.PLAYER1;
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
    public GridOption getCurrentPlayerTurn() {// espongo turn, lo devo
                                              // proteggere?
        return this.turn;
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
    public void setLine(final ListType list, final Integer listIndex, final Integer position) {

        switch (list) {
        case HORIZONTAL:
            grid.setHorizontalLine(listIndex, position, this.turn);
            if (horizontalPointScored(listIndex, position) > 0) {
                addPoints(horizontalPointScored(listIndex, position));
            } else {
                nextTurn();
            }
            break;
        case VERTICAL:
            grid.setVerticalLine(listIndex, position, this.turn);
            if (verticalPointScored(listIndex, position) > 0) {
                addPoints(verticalPointScored(listIndex, position));
            } else {
                nextTurn();
            }
            break;
        default:
            throw new IllegalArgumentException("the list selected does not exist");
        }

        LastMove lastMove = new LastMoveImpl();
        lastMove.setLastListType(list);
        lastMove.setLastListIndex(listIndex);
        lastMove.setLastPosition(position);
        lastMoveList.add(lastMove);
    }

    private GridOption getPreviousParallelList(final ListType list, final int listIndex, final int position) {
        if (listIndex > 0) {
            switch (list) {
            case HORIZONTAL:
                return grid.getCopyOfHorizontalElement(listIndex - 1, position);
            case VERTICAL:
                return grid.getCopyOfVerticalElement(listIndex - 1, position);
            default:
                throw new IllegalStateException("the list does not exist");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private GridOption getNextParallelList(final ListType list, final int listIndex, final int position) {
        switch (list) {
        case HORIZONTAL:
            if (listIndex < grid.getHorizontalListSize()) {
                return grid.getCopyOfHorizontalElement(listIndex + 1, position);
            } else {
                throw new IllegalArgumentException();
            }
        case VERTICAL:
            if (listIndex < grid.getVerticallListSize()) {
                return grid.getCopyOfVerticalElement(listIndex + 1, position);
            } else {
                throw new IllegalArgumentException();
            }
        default:
            throw new IllegalStateException("the list does not exist");
        }
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
            if (getPreviousParallelList(ListType.VERTICAL, listIndex, position) != GridOption.EMPTY) {
                if (grid.getCopyOfHorizontalElement(position, listIndex - 1) != GridOption.EMPTY
                        && grid.getCopyOfHorizontalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        if (listIndex < grid.getVerticallListSize() - 1) {
            if (getNextParallelList(ListType.VERTICAL, listIndex, position) != GridOption.EMPTY) {
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

        if (this.turn.equals(GridOption.PLAYER1)) {
            scorePlayer1 += points;
        } else {
            scorePlayer2 += points;
        }
    }

    @Override
    public void undoLastMove() {

        if (lastMoveList.isEmpty()) {
            throw new IllegalStateException("you can't undo if you didn't made a move");
        }

        if (getCopyOfLastMove().getLastListType().equals(ListType.HORIZONTAL)) {
            addPoints(-horizontalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()));
            grid.setHorizontalLine(getCopyOfLastMove().getLastListIndex(), getCopyOfLastMove().getLastPosition(),
                    GridOption.EMPTY);
            if (!(horizontalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()) > 0)) {
                nextTurn();
            }
        } else {
            addPoints(-verticalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()));
            grid.setVerticalLine(getCopyOfLastMove().getLastListIndex(), getCopyOfLastMove().getLastPosition(),
                    GridOption.EMPTY);
            if (!(verticalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()) > 0)) {
                nextTurn();
            }
        }

        lastMoveList.remove(lastMoveList.size() - 1);
    }

    @Override
    public LastMove getCopyOfLastMove() {
        return lastMoveList.get(lastMoveList.size() - 1);
    }

    @Override // DA RIGUARDARE
    public BaseGrid getCopyOfGrid() {
        BaseGrid copyOfThisGrid = this.grid;
        return copyOfThisGrid;
    }
}
