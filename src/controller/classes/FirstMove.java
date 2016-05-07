package controller.classes;

import model.classes.TurnImpl;
import model.enumerations.GridOption;
import model.interfaces.BaseGrid;

public class FirstMove extends AddMove {

    private GridOption firstMove;

    public FirstMove(BaseGrid grid) {
        super.turn = new TurnImpl(grid);
    }

    public GridOption firstPlayer() {
        turn.startMatch();
        this.firstMove = turn.getCurrentPlayerTurn();
        return this.firstMove;
    }
}
