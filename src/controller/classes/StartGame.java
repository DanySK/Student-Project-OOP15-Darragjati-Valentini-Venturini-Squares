package controller.classes;

import controller.enumerations.TypeGame;
import model.classes.BaseGridImpl;
import model.classes.PlayerImpl;
import model.classes.TriangleGridImpl;
import model.classes.TurnImpl;
import model.enumerations.GridOption;
import model.interfaces.BaseGrid;
import model.interfaces.Turn;

public class StartGame {

    private final int columnsNumber;
    private final int rowsNumber;
    private final String namePlayer1;
    private final String namePlayer2;
    private final TypeGame mode;
    private GridOption firstPlayer;
    private FirstMove firstMove;
    private String nameFirstPlayer;

    public StartGame(int columsNumber, int rowsNumber, String namePlayer1, String namePlayer2, TypeGame mode) {
        controlNamePlayers(namePlayer1, namePlayer2);
        this.columnsNumber = columsNumber;
        this.rowsNumber = rowsNumber;
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        this.mode = mode;
        this.firstPlayer = null;

    }

    public String createGrid() {
        BaseGrid newGrid;
        switch (this.mode) {
        case SQUARE:
            newGrid = new BaseGridImpl(this.rowsNumber, this.columnsNumber);
            break;
        case TRIANGLE:
            newGrid = new TriangleGridImpl(this.rowsNumber, this.columnsNumber);
            break;
        default:
            throw new IllegalStateException();

        }
        this.firstMove = new FirstMove(newGrid);
        this.firstPlayer = firstMove.firstPlayer();
        randomPlayer();
        return this.nameFirstPlayer;
    }

    private void randomPlayer() {
        switch (this.firstPlayer) {
        case PLAYER1:
            this.nameFirstPlayer = this.namePlayer1;
            break;
        case PLAYER2:
            this.nameFirstPlayer = this.namePlayer2;
            break;
        default:
            throw new IllegalStateException();
        }
    }

    private void controlNamePlayers(String namePlayer1, String namePlayer2) {
        if (namePlayer1.equals(namePlayer2)) {
            throw new IllegalArgumentException();
        }

    }

    public int getNumColonne() {
        return this.columnsNumber;
    }

    public int getNumRighe() {
        return this.rowsNumber;
    }

    public String getNamePlayer1() {
        return this.namePlayer1;
    }

    public String getNamePlayer2() {
        return this.namePlayer2;
    }

}
