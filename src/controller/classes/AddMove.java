package controller.classes;

import model.classes.GameImpl;
import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.Turn;

public class AddMove {
    private ListType direction;
    private int position;
    private int numLine;
    private Turn turn;
    private GridOption firstMove;

    public AddMove(BaseGrid grid) {
        this.turn = new GameImpl(grid);
    }

    public AddMove(final ListType direction, final int numLine, final int position) {
        this.direction = direction;
        this.numLine = numLine;
        this.position = position;

    }

    public void addLine() {
        turn.setLine(this.direction, this.numLine, this.position);
    }
    
    public GridOption firstPlayer() {
        turn.startMatch();
        this.firstMove = turn.getCurrentPlayerTurn();
        return this.firstMove;
    }
}
