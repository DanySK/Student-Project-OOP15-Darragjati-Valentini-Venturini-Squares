package controller.classes;

import model.classes.GameImpl;
import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.Turn;

public class AddMove {
    // private ListType direction;
    // private int position;
    // private int numLine;
    private Turn turn;
    private GridOption firstMove;

    public AddMove(BaseGrid grid) {
        this.turn = new GameImpl(grid);
    }

    public void addLine(ListType direction, int numLine, int position) {
        turn.setLine(direction, numLine, position);
    }

    public GridOption firstPlayer() {
        turn.startMatch();
        this.firstMove = turn.getCopyOfCurrentPlayerTurn();
        return this.firstMove;
    }
}
