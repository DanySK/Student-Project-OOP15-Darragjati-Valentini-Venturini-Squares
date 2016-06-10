package it.unibo.squaresgameteam.squares.controller.classes;

import java.io.IOException;

import it.unibo.squaresgameteam.squares.controller.enumerations.TypeGame;
import it.unibo.squaresgameteam.squares.controller.interfaces.Match;
import it.unibo.squaresgameteam.squares.controller.interfaces.ShowRanking;
import it.unibo.squaresgameteam.squares.model.classes.SquareGridImpl;
import it.unibo.squaresgameteam.squares.model.classes.GameImpl;
import it.unibo.squaresgameteam.squares.model.classes.MoveImpl;
import it.unibo.squaresgameteam.squares.model.classes.PlayedTimeImpl;
import it.unibo.squaresgameteam.squares.model.classes.TriangleGridImpl;
import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.enumerations.ListType;
import it.unibo.squaresgameteam.squares.model.exceptions.UnsupportedSizeException;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.exceptions.NoMovesDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.SquareGrid;
import it.unibo.squaresgameteam.squares.model.interfaces.Move;
import it.unibo.squaresgameteam.squares.model.interfaces.PlayedTime;
import it.unibo.squaresgameteam.squares.model.interfaces.Player;
import it.unibo.squaresgameteam.squares.model.interfaces.Game;

/**
 * 
 * @author Licia Valentini Classe creata per gestire la partita, dalla prima
 *         all'ultima mossa.
 *
 */
public class MatchImpl implements Match {

    private final int columsNumber;
    private final int rowsNumber;
    private final String namePlayer1;
    private final String namePlayer2;
    private final TypeGame mode;
    private GridOption numPlayer;
    private String namePlayer;
    private SquareGrid grid;
    private Game match;
    private PlayedTime time;
    private int playerScore;
    private GridOption winner;
    private boolean endMatch;

    /**
     * 
     * @param columsNumber number of colums
     * @param rowsNumber   number of rows     
     * @param namePlayer1 name first player
     * @param namePlayer2 name second player
     * @param mode game mode
     *            Costruttore della classe.
     */
    public MatchImpl(final int columsNumber, final int rowsNumber, final String namePlayer1, final String namePlayer2,
            final TypeGame mode) {
        controlNamePlayers(namePlayer1, namePlayer2);
        this.columsNumber = columsNumber;
        this.rowsNumber = rowsNumber;
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        this.mode = mode;
        this.endMatch = false;
    }

    @Override
    public void createGrid() throws UnsupportedSizeException {

        switch (this.mode) {
        case SQUARE:
            this.grid = new SquareGridImpl(this.rowsNumber, this.columsNumber);
            break;
        case TRIANGLE:
            this.grid = new TriangleGridImpl(this.rowsNumber, this.columsNumber);
            break;
        default:
            throw new IllegalStateException();

        }

    }

    @Override
    public String createNewMatch() {
        this.match = new GameImpl(this.grid, namePlayer1, namePlayer2);
        match.startMatch();
        this.numPlayer = this.match.getCurrentPlayerTurn();
        convertNumToNamePlayer();
        this.time = new PlayedTimeImpl();
        return this.namePlayer;
    }

    private void convertNumToNamePlayer() {
        switch (this.numPlayer) {
        case PLAYER1:
            this.namePlayer = this.namePlayer1;
            break;
        case PLAYER2:
            this.namePlayer = this.namePlayer2;
            break;
        default:
            throw new IllegalStateException();
        }

    }

    @Override
    public String addLine(final ListType direction, final int numLine, final int position)
            throws UnexistentLineListException, IOException, DuplicatedPlayerStatsException, ClassNotFoundException {

        final Move addMove = new MoveImpl(direction, numLine, position);

        this.match.setLine(addMove);
        if (this.match.isEnded()) {
            this.endMatch = true;
            this.numPlayer = this.match.getWinner();
            this.winner = numPlayer;
            addPlayerRank();
            this.numPlayer = this.winner;

        } else {
            this.playerScore = this.match.getPlayerPoints(this.numPlayer);
            this.numPlayer = this.match.getCurrentPlayerTurn();
        }

        convertNumToNamePlayer();
        return this.namePlayer;
    }

    @Override
    public int getPlayerScore() {
        return this.playerScore;
    }

    @Override
    public Move undo() throws NoMovesDoneException, UnexistentLineListException {
        this.match.undoLastMove();
        return this.match.getCopyOfLastMove();

    }

    @Override
    public Double getMatchTime() {
        return this.time.getTotalMatchDuration();
    }

    private void controlNamePlayers(final String namePlayer1, final String namePlayer2) {
        if (namePlayer1.equals(namePlayer2)) {
            throw new IllegalArgumentException();
        }
    }

    private void addPlayerRank() throws IOException, DuplicatedPlayerStatsException, ClassNotFoundException {
        final ShowRanking ranking = new ShowRankingImpl();
        Player playerResult = this.match.getPlayerMatchResult(numPlayer);
        ranking.addPlayer(playerResult);
        if (this.numPlayer.equals(GridOption.PLAYER1)) {
            this.numPlayer = GridOption.PLAYER2;
        } else {
            this.numPlayer = GridOption.PLAYER1;
        }
        playerResult = this.match.getPlayerMatchResult(numPlayer);
        ranking.addPlayer(playerResult);
    }

    @Override
    public boolean getEndMatch() {
        return this.endMatch;
    }

    @Override
    public String getNamePlayer1() {
        return this.namePlayer1;
    }

    @Override
    public String getNamePlayer2() {
        return this.namePlayer2;
    }

    @Override
    public int getColumsNumber() {
        return this.columsNumber;
    }
    
    @Override
    public int getRowsNumber() {
        return this.rowsNumber;
    }
    
    @Override
    public TypeGame getMode() {
        return this.mode;
    }

}
