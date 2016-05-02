package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.LastMove;
import model.interfaces.TriangleGrid;
import model.interfaces.Turn;

public class TurnImpl implements Turn {

    private BaseGrid grid;
    private boolean matchStarted = false;
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private static final Integer INITIAL_SCORE = 0;
    private GridOption turn = GridOption.EMPTY;
    private List<LastMove> lastMoveList = new ArrayList<>();

    public TurnImpl(BaseGrid grid) {
        this.grid = grid;
    }

    @Override
    public void startMatch() {
        if (!isStarted()) {
            this.scorePlayer1 = INITIAL_SCORE;
            this.scorePlayer2 = INITIAL_SCORE;
            randomizeTurn();
            matchStarted = true;
            // new BaseGridImpl(rowsNumber, columnsNumber);
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
        Integer diagonalMOves = 0;
        if (this.grid.getClass().equals(TriangleGridImpl.class)) {
            diagonalMOves = (this.grid.getHorizontalListSize() - 1) * (this.grid.getVerticallListSize() - 1);
        }
        return getPlayerPoints(GridOption.PLAYER1)
                + getPlayerPoints(GridOption.PLAYER2) == (grid.getHorizontalListSize() - 1)
                        * (grid.getVerticallListSize() - 1) + diagonalMOves ? true : false;
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

        Integer points = 0;

        switch (list) {
        case HORIZONTAL:
            grid.setHorizontalLine(listIndex, position, this.turn);
            points = grid.horizontalPointScored(listIndex, position);
            if (points > 0) {
                addPoints(points);
            } else {
                nextTurn();
            }
            break;
        case VERTICAL:
            grid.setVerticalLine(listIndex, position, this.turn);
            points = grid.verticalPointScored(listIndex, position);
            if (points > 0) {
                addPoints(points);
            } else {
                nextTurn();
            }
            break;
        case DIAGONAL:
            if (grid.getClass().equals(BaseGridImpl.class)) {
                throw new UnsupportedOperationException();
            }
            ((TriangleGrid) grid).setDiagonalLine(listIndex, position, this.turn);
            points = ((TriangleGrid) grid).diagonalPointScored(listIndex, position);
            if (points > 0) {
                addPoints(points);
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

        switch (getCopyOfLastMove().getLastListType()) {
        case HORIZONTAL:
            addPoints(-grid.horizontalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()));
            grid.setHorizontalLine(getCopyOfLastMove().getLastListIndex(), getCopyOfLastMove().getLastPosition(),
                    GridOption.EMPTY);
            if (!(grid.horizontalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()) > 0)) {
                nextTurn();
            }
            break;
        case VERTICAL:
            addPoints(-grid.verticalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()));
            grid.setVerticalLine(getCopyOfLastMove().getLastListIndex(), getCopyOfLastMove().getLastPosition(),
                    GridOption.EMPTY);
            if (!(grid.verticalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()) > 0)) {
                nextTurn();
            }
            break;
        case DIAGONAL:
            addPoints(-((TriangleGrid) grid).diagonalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()));
            ((TriangleGrid) grid).setDiagonalLine(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition(), GridOption.EMPTY);
            if (!(((TriangleGrid) grid).diagonalPointScored(getCopyOfLastMove().getLastListIndex(),
                    getCopyOfLastMove().getLastPosition()) > 0)) {
                nextTurn();
            }
            break;
        }
        lastMoveList.remove(lastMoveList.size() - 1);
    }

    @Override
    public LastMove getCopyOfLastMove() {
        return lastMoveList.get(lastMoveList.size() - 1);
    }
}
