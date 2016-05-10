package controller.classes;

import controller.enumerations.TypeGame;
import model.classes.BaseGridImpl;
import model.classes.GameImpl;
import model.classes.TriangleGridImpl;
import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.Turn;

public class MatchImpl {

    private final int columnsNumber;
    private final int rowsNumber;
    private final String namePlayer1;
    private final String namePlayer2;
    private final TypeGame mode;
    private GridOption numPlayer;
    private String namePlayer;
    private GridOption firstMove;
    private BaseGrid grid;
    private GameImpl match;

    public MatchImpl(int columsNumber, int rowsNumber, String namePlayer1, String namePlayer2, TypeGame mode) {
        controlNamePlayers(namePlayer1, namePlayer2);
        this.columnsNumber = columsNumber;
        this.rowsNumber = rowsNumber;
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        this.mode = mode;
        this.numPlayer = null;
    }

    public void createGrid() {

        switch (this.mode) {
        case SQUARE:
            this.grid = new BaseGridImpl(this.rowsNumber, this.columnsNumber);
            break;
        case TRIANGLE:
            this.grid = new TriangleGridImpl(this.rowsNumber, this.columnsNumber);
            break;
        default:
            throw new IllegalStateException();

        }

    }

    public String createNewMatch() {
        this.match = new GameImpl(this.grid);
        match.startMatch();
        this.numPlayer = match.getCopyOfCurrentPlayerTurn();
        convertNumToNamePlayer();
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

    public void addLine(ListType direction, int numLine, int position) {
        this.match.setLine(direction, numLine, position);
    }

    public void undo() {
        this.match.undoLastMove();
    }

    private void controlNamePlayers(String namePlayer1, String namePlayer2) {
        if (namePlayer1.equals(namePlayer2)) {
            throw new IllegalArgumentException();
        }
    }

}
