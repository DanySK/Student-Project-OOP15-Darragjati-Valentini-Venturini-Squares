package controller.classes;

import model.classes.GameImpl;
import model.enumerations.GridOption;
import model.interfaces.BaseGrid;

public class FirstMove extends AddMove {

    private GridOption firstMove;

    public FirstMove(BaseGrid grid) {
        super(grid);
        
    }

    public GridOption firstPlayer() {
        turn.startMatch();
        this.firstMove = turn.getCurrentPlayerTurn();
        return this.firstMove;
    }
}
