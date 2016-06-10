package it.unibo.squaresgameteam.squares.model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.exceptions.NoMovesDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.Move;
import it.unibo.squaresgameteam.squares.model.interfaces.PlayedTime;
import it.unibo.squaresgameteam.squares.model.interfaces.Player;
import it.unibo.squaresgameteam.squares.model.interfaces.PointsCounterStrategy;
import it.unibo.squaresgameteam.squares.model.interfaces.BaseGrid;
import it.unibo.squaresgameteam.squares.model.interfaces.Game;

/**
 * This class is used to start a new match and all the things related to it,
 * like assigning points to a player or knowing when the game is ended.
 */
public class GameImpl implements Game {

    private final BaseGrid grid;
    private boolean matchStarted;
    private Player player1;
    private Player player2;
    private final String namePlayer1;
    private final String namePlayer2;
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private static final Integer INITIAL_SCORE = 0;
    private GridOption turn = GridOption.EMPTY;
    private final List<Move> lastMoveList;
    private final PointsCounterStrategy calculatePoints;
    private final PlayedTime playedTime;

    /**
     * This constructor takes an object that implements BaseGrid.
     * 
     * @param grid
     *            the playable game field.
     * @param namePlayer1
     *            the name of the first player
     * @param namePlayer2
     *            the name of the second player
     */
    // CHECKSTYLE:OFF:
    public GameImpl(final BaseGrid grid, final String namePlayer1, final String namePlayer2) {
        // CHECKSTYLE:ON:
        this.grid = grid;
        this.matchStarted = false;
        if (grid instanceof TriangleGridImpl) {
            calculatePoints = new TriangleGridPointsCounter((TriangleGridImpl) grid);
        } else {
            if (grid instanceof SquareGridImpl) {
                calculatePoints = new SquareGridPointsCounter((SquareGridImpl) grid);
        } else {
                throw new IllegalArgumentException();
            }
        }
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        lastMoveList = new ArrayList<>();
        playedTime = new PlayedTimeImpl();
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
            ((PlayedTimeImpl) playedTime).setTimeAtMatchStart(isStarted());
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
        return getPlayerPoints(GridOption.PLAYER1) + getPlayerPoints(GridOption.PLAYER2) == grid.getMatchMaximumPoints()
                ? true : false;
    }

    @Override
    public GridOption getCurrentPlayerTurn() {
        if (isStarted()) {
            return this.turn;
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

    private void createPlayers() {
        if (isEnded()) {
            player1 = new PlayerImpl.Builder()
                                    .playerName(namePlayer1)
                                    .wonMatches(0)
                                    .totalMatches(1)
                                    .totalPointsScored(scorePlayer1)
                                    .build();
            player2 = new PlayerImpl.Builder()
                                    .playerName(namePlayer2)
                                    .wonMatches(0)
                                    .totalMatches(1)
                                    .totalPointsScored(scorePlayer2)
                                    .build();
            switch (getWinner()) {
            case PLAYER1:
                player1 = new PlayerImpl.Builder()
                                        .playerName(namePlayer1)
                                        .wonMatches(1)
                                        .totalMatches(1)
                                        .totalPointsScored(scorePlayer1)
                                        .build();
                break;
            case PLAYER2:
                player2 = new PlayerImpl.Builder()
                                        .playerName(namePlayer2)
                                        .wonMatches(1)
                                        .totalMatches(1)
                                        .totalPointsScored(scorePlayer2)
                                        .build();
                break;
            default:    
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Player getPlayerMatchResult(final GridOption player) {
        if (isEnded()) {
            switch (player) {
            case PLAYER1:
                return player1;
            case PLAYER2:
                return player2;
            default:
                throw new IllegalArgumentException();

            }
        } else {
            throw new IllegalStateException();
        }
    }
    
    @Override
    public Double getMatchDuration() {
        return playedTime.getTotalMatchDuration();
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
    public void setLine(final Move move) throws UnexistentLineListException {
        if (!this.isStarted()) {
            throw new IllegalStateException();
        }
        grid.setLine(move, this.turn);
        final Move lastMove = new MoveImpl(move.getListType(), move.getListIndex(), move.getPosition());
        addPoints(calculatePoints.getPoints(lastMove));
        if (isEnded()) {
            ((PlayedTimeImpl) playedTime).calculateMatchDuration(isEnded());
            createPlayers();
        }
        lastMoveList.add(lastMove);
    }

    @Override
    public void undoLastMove() throws NoMovesDoneException, UnexistentLineListException {
        if (lastMoveList.isEmpty()) {
            throw new NoMovesDoneException();
        }
        grid.setLine(getCopyOfLastMove(), GridOption.EMPTY);
        addPoints(-calculatePoints.getPoints(getCopyOfLastMove()));
        lastMoveList.remove(lastMoveList.size() - 1);
    }

    @Override
    public Move getCopyOfLastMove() throws NoMovesDoneException {
        if (lastMoveList.isEmpty()) {
            throw new NoMovesDoneException();
        } else {
            return new MoveImpl(lastMoveList.get(lastMoveList.size() - 1).getListType(),
                    lastMoveList.get(lastMoveList.size() - 1).getListIndex(),
                    lastMoveList.get(lastMoveList.size() - 1).getPosition());
        }
    }
}
