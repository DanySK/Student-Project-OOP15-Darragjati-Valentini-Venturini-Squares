package controller.classes;

import controller.enumerations.TypeGame;
import controller.interfaces.Match;
import model.classes.SquareGridImpl;
import model.classes.GameImpl;
import model.classes.PlayedTimeImpl;
import model.classes.TriangleGridImpl;
import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.SquareGrid;
import model.interfaces.Move;
import model.interfaces.PlayedTime;
import model.interfaces.Game;

public class MatchImpl implements Match {

    private final int columnsNumber;
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

    public MatchImpl(int columsNumber, int rowsNumber, String namePlayer1, String namePlayer2, TypeGame mode) {
        controlNamePlayers(namePlayer1, namePlayer2);
        this.columnsNumber = columsNumber;
        this.rowsNumber = rowsNumber;
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        this.mode = mode;
    }

    @Override
    public void createGrid() {

        switch (this.mode) {
        case SQUARE:
            this.grid = new SquareGridImpl(this.rowsNumber, this.columnsNumber);
            break;
        case TRIANGLE:
            this.grid = new TriangleGridImpl(this.rowsNumber, this.columnsNumber);
            break;
        default:
            throw new IllegalStateException();

        }

    }

    @Override
    public String createNewMatch() {
        this.match = new GameImpl(this.grid);
        match.startMatch();
        this.numPlayer = this.match.getCopyOfCurrentPlayerTurn();
        convertNumToNamePlayer();
        this.time = new PlayedTimeImpl();
        this.time.setTimeAtMatchStart(match);
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
    public String addLine(ListType direction, int numLine, int position) {
        this.match.setLine(direction, numLine, position);
        if (this.match.isEnded()) {
            this.time.calculateMatchDuration(match);
            this.numPlayer = this.match.getWinner();

        } else {
            this.playerScore = this.match.getPlayerPoints(this.numPlayer);
            this.numPlayer = this.match.getCopyOfCurrentPlayerTurn();
        }

        convertNumToNamePlayer();
        return this.namePlayer;
    }

    @Override
    public int getPlayerScore() {

        return this.playerScore;
    }

    @Override
    public Move undo() {
        this.match.undoLastMove();
        return this.match.getCopyOfLastMove();

    }

    @Override
    public Double getMatchTime() {
        return this.time.getTotalMatchDuration();
    }

    private void controlNamePlayers(String namePlayer1, String namePlayer2) {
        if (namePlayer1.equals(namePlayer2)) {
            throw new IllegalArgumentException();
        }
    }

}
