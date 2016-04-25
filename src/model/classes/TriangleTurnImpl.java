package model.classes;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.LastMove;

public class TriangleTurnImpl extends TurnImpl{

    public TriangleTurnImpl(Integer rowsNumber, Integer columnNumber) {
        super(rowsNumber, columnNumber);
    }

    @Override
    public boolean isEnded() {

        if (!isStarted()) {
            throw new IllegalStateException("the match can't be ended if it isn't even started");
        }

        return getPlayerPoints(GridOption.PLAYER1)
                + getPlayerPoints(GridOption.PLAYER2) == ((grid.getHorizontalListSize() - 1)
                        * (grid.getVerticallListSize() - 1)) * 2 ? true : false;
    }
    
    @Override
    public void setLine(final ListType list, final Integer listIndex, final Integer position) {

        switch (list) {
        case HORIZONTAL:
            grid.setHorizontalLine(listIndex, position, this.turn);
            if (horizontalPointScored(listIndex, position) > 0) {
                addPoints(horizontalPointScored(listIndex, position));
            } else {
                nextTurn();
            }
            break;
        case VERTICAL:
            grid.setVerticalLine(listIndex, position, this.turn);
            if (verticalPointScored(listIndex, position) > 0) {
                addPoints(verticalPointScored(listIndex, position));
            } else {
                nextTurn();
            }
            break;
        default:
            throw new IllegalArgumentException("the list selected does not exist");
        }

        LastMove lastMove = new LastMoveImpl();
        lastMove.setLastListType(list);
        lastMove.setLastListIndex(listIndex);
        lastMove.setLastPosition(position);
        lastMoveList.add(lastMove);
    }
    
    private Integer horizontalPointScored(final int listIndex, final int position){
         
    }
    
    private Integer verticalPointScored(final int listIndex, final int position) {
        
    }
        
    private Integer diagonalPointScored(final int listIndex, final int position) {
        
    }
    
    @Override
    public void undoLastMove() {
        
    }
}
