package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.exceptions.NoMovesDoneException;
import model.exceptions.UnexistentLineListException;
import model.interfaces.SquareGrid;
import model.interfaces.Move;
import model.interfaces.TriangleGrid;
import model.interfaces.Game;

/**
 * This class is used to start a new match and all the things related to it,
 * like assigning points to a player or knowing when the game is ended.
 */
public class GameImpl implements Game {

    private final SquareGrid grid;
    private boolean matchStarted;
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private static final Integer INITIAL_SCORE = 0;
    private GridOption turn = GridOption.EMPTY;
    private final List<Move> lastMoveList = new ArrayList<>();

    /**
     * This constructor takes an object that implements BaseGrid.
     * 
     * @param grid the playable game field.
     */
    // CHECKSTYLE:OFF:
    public GameImpl(final SquareGrid grid) {
        // CHECKSTYLE:ON:
        this.grid = grid;
        this.matchStarted = false;
    }

    @Override
    public void startMatch() {
        if (isStarted()) {
            throw new IllegalStateException("Match already started");
        } else {
            this.scorePlayer1 = INITIAL_SCORE;
            this.scorePlayer2 = INITIAL_SCORE;
            randomizeTurn();
            this.matchStarted = true;
        }
    }

    private void randomizeTurn() {
        if (isStarted()) {
            throw new IllegalStateException();
        } else {
            final Random randomTurn = new Random();
            if (randomTurn.nextInt(2) == 0) {
                this.turn = GridOption.PLAYER1;
            } else {
                this.turn = GridOption.PLAYER2;
            }
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
        Integer diagonalMOves = 0;
        if (this.grid.getClass().equals(TriangleGridImpl.class)) {
            diagonalMOves = (this.grid.getHorizontalListSize() - 1) * (this.grid.getVerticallListSize() - 1);
        }
        return getPlayerPoints(GridOption.PLAYER1)
                + getPlayerPoints(GridOption.PLAYER2) == (grid.getHorizontalListSize() - 1)
                        * (grid.getVerticallListSize() - 1) + diagonalMOves ? true : false;
    }

    @Override
    public GridOption getCopyOfCurrentPlayerTurn() {
        if (isStarted()) {
            final GridOption copyOfTurn = this.turn;
            // CHECKSTYLE:OFF:
            return copyOfTurn;
            // CHECKSTYLE:ON:
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Integer getPlayerPoints(final GridOption player) {
        if (!isStarted()) {
            return INITIAL_SCORE;
        }
        if (player.equals(GridOption.PLAYER1) || player.equals(GridOption.PLAYER2)) {
            return player.equals(GridOption.PLAYER1) ? scorePlayer1 : scorePlayer2;
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
            return getPlayerPoints(GridOption.PLAYER1) > getPlayerPoints(GridOption.PLAYER2) ? GridOption.PLAYER1
                    : GridOption.PLAYER2;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void setLine(final Move move) throws UnexistentLineListException {
        Integer points = 0;
        final ListType list = move.getListType();
        final Integer listIndex = move.getListIndex();
        final Integer position = move.getPosition();

        switch (list) {
        case HORIZONTAL:
            points = grid.setHorizontalLine(listIndex, position, this.turn);
            addPoints(points);
            break;
        case VERTICAL:
            points = grid.setVerticalLine(listIndex, position, this.turn);
            addPoints(points);
            break;
        case DIAGONAL:
            if (grid.getClass().equals(TriangleGridImpl.class)) {
                points = ((TriangleGrid) grid).setDiagonalLine(listIndex, position, this.turn);
                addPoints(points);
                break;
            } else {
                throw new UnsupportedOperationException("the diagonal move is not supported by the selected game mode");
            }
        default:
            throw new IllegalArgumentException("the list selected does not exist");
        }

        final Move lastMove = new MoveImpl(list, listIndex, position);
        lastMoveList.add(lastMove);
    }

    private void addPoints(final Integer points) {
        if (!isStarted()) {
            throw new IllegalStateException();
        }
        if (points == 0) {
            nextTurn();
        } else {
            switch (this.turn) {
            case PLAYER1:
                scorePlayer1 += points;
                break;
            case PLAYER2:
                scorePlayer2 += points;
                break;
            default:
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void undoLastMove() throws NoMovesDoneException, UnexistentLineListException {

        if (lastMoveList.isEmpty()) {
            throw new NoMovesDoneException();
        }
        Integer points = 0;
        switch (getCopyOfLastMove().getListType()) {
        case HORIZONTAL:
            points = grid.setHorizontalLine(getCopyOfLastMove().getListIndex(), getCopyOfLastMove().getPosition(),
                    GridOption.EMPTY);
            addPoints(-points);
            break;
        case VERTICAL:
            points = grid.setVerticalLine(getCopyOfLastMove().getListIndex(), getCopyOfLastMove().getPosition(),
                    GridOption.EMPTY);
            addPoints(-points);
            break;
        case DIAGONAL:
            points = ((TriangleGrid) grid).setDiagonalLine(getCopyOfLastMove().getListIndex(),
                    getCopyOfLastMove().getPosition(), GridOption.EMPTY);
            addPoints(-points);
            break;
        default:
            throw new IllegalArgumentException();
        }
        lastMoveList.remove(lastMoveList.size() - 1);
    }

    @Override
    public Move getCopyOfLastMove() throws NoMovesDoneException {
        if (lastMoveList.isEmpty()) {
            throw new NoMovesDoneException();
        } else {
            return lastMoveList.get(lastMoveList.size() - 1);
        }
    }
}
