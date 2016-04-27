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

    protected BaseGrid squareGrid;
    protected boolean matchStarted = false;
    protected Integer scorePlayer1;
    protected Integer scorePlayer2;
    protected Integer rowsNumber;
    protected Integer columnsNumber;
    protected static final Integer INITIAL_SCORE = 0;
    protected GridOption turn = GridOption.EMPTY;
    protected List<LastMove> lastMoveList = new ArrayList<>();

    public TurnImpl(final Integer rowsNumber, final Integer columnsNumber) {
        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;
    }

    @Override
    public void startMatch() {
        if (!isStarted()) {
            this.scorePlayer1 = INITIAL_SCORE;
            this.scorePlayer2 = INITIAL_SCORE;
            randomizeTurn();
            matchStarted = true;
            squareGrid = new BaseGridImpl(rowsNumber, columnsNumber);
        } else {
            throw new IllegalStateException("Match already started");
        }
    }

    protected void randomizeTurn() {

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

    protected void nextTurn() {

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
                + getPlayerPoints(GridOption.PLAYER2) == (squareGrid.getHorizontalListSize() - 1)
                        * (squareGrid.getVerticallListSize() - 1) ? true : false;
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
            squareGrid.setHorizontalLine(listIndex, position, this.turn);
            if (horizontalPointScored(listIndex, position) > 0) {
                addPoints(horizontalPointScored(listIndex, position));
            } else {
                nextTurn();
            }
            break;
        case VERTICAL:
            squareGrid.setVerticalLine(listIndex, position, this.turn);
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
    //da tenere?
    private GridOption getPreviousParallelList(final ListType list, final int listIndex, final int position) {
        if (listIndex > 0) {
            switch (list) {
            case HORIZONTAL:
                return squareGrid.getCopyOfHorizontalElement(listIndex - 1, position);
            case VERTICAL:
                return squareGrid.getCopyOfVerticalElement(listIndex - 1, position);
            default:
                throw new IllegalStateException("the list does not exist");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    //da tenere?
    private GridOption getNextParallelList(final ListType list, final int listIndex, final int position) {
        switch (list) {
        case HORIZONTAL:
            if (listIndex < squareGrid.getHorizontalListSize()) {
                return squareGrid.getCopyOfHorizontalElement(listIndex + 1, position);
            } else {
                throw new IllegalArgumentException();
            }
        case VERTICAL:
            if (listIndex < squareGrid.getVerticallListSize()) {
                return squareGrid.getCopyOfVerticalElement(listIndex + 1, position);
            } else {
                throw new IllegalArgumentException();
            }
        default:
            throw new IllegalStateException("the list does not exist");
        }
    }

    protected Integer horizontalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex > 0) {
            if (!squareGrid.getCopyOfHorizontalElement(listIndex - 1, position).equals(GridOption.EMPTY)) {
                if (!squareGrid.getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                        && !squareGrid.getCopyOfVerticalElement(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
                    points++;
                }
            }
        }

        if (listIndex < squareGrid.getHorizontalListSize() - 1) {
            if (!squareGrid.getCopyOfHorizontalElement(listIndex + 1, position).equals(GridOption.EMPTY)) {
                if (!squareGrid.getCopyOfVerticalElement(position, listIndex).equals(GridOption.EMPTY)
                        && !squareGrid.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)) {
                    points++;
                }
            }
        }
        return points;
    }

    protected Integer verticalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex > 0) {
            if (!getPreviousParallelList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)) {
                if (!squareGrid.getCopyOfHorizontalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                        && !squareGrid.getCopyOfHorizontalElement(position + 1, listIndex - 1).equals(GridOption.EMPTY)) {
                    points++;
                }
            }
        }

        if (listIndex < squareGrid.getVerticallListSize() - 1) {
            if (!getNextParallelList(ListType.VERTICAL, listIndex, position).equals(GridOption.EMPTY)) {
                if (!squareGrid.getCopyOfHorizontalElement(position, listIndex).equals(GridOption.EMPTY)
                        && !squareGrid.getCopyOfHorizontalElement(position + 1, listIndex).equals(GridOption.EMPTY)) {
                    points++;
                }
            }
        }
        return points;
    }

    protected void addPoints(final Integer points) {

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
            squareGrid.setHorizontalLine(getCopyOfLastMove().getLastListIndex(), getCopyOfLastMove().getLastPosition(),
                    GridOption.EMPTY);
            if (!(horizontalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()) > 0)) {
                nextTurn();
            }
        } else {
            addPoints(-verticalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()));
            squareGrid.setVerticalLine(getCopyOfLastMove().getLastListIndex(), getCopyOfLastMove().getLastPosition(),
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
        BaseGrid copyOfThisGrid = this.squareGrid;
        return copyOfThisGrid;
    }
}
