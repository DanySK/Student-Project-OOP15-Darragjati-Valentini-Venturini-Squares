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
    private GridOption firstMove;
    private String firstPlayer;
    private Turn newTurn;

    public StartGame(int columsNumber, int rowsNumber, String namePlayer1, String namePlayer2, TypeGame mode) {
        controlNamePlayers(namePlayer1, namePlayer2);
        this.columnsNumber = columsNumber;
        this.rowsNumber = rowsNumber;
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        this.mode = mode;
        this.firstMove = null;
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
            
        default:
            throw new IllegalStateException();

        }
        firstPlayer(newGrid);
        return this.firstPlayer;

    }

    private void controlNamePlayers(String namePlayer1, String namePlayer2) {
        if (namePlayer1.equals(namePlayer2)) {
            throw new IllegalArgumentException();
        }

    }

    private void firstPlayer(BaseGrid newGrid) {
        newTurn = new TurnImpl(newGrid);
        newTurn.startMatch();
        this.firstMove = newTurn.getCurrentPlayerTurn();
        switch (this.firstMove) {
        case PLAYER1:
            this.firstPlayer = this.namePlayer1;
            break;
        case PLAYER2:
            this.firstPlayer = this.namePlayer2;
            break;
        default:
            throw new IllegalStateException();
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

    public String getFirstPlayer() {
        return this.firstPlayer;
    }
    
    public Turn getNewTurn(){
        return this.newTurn;
    }
}
