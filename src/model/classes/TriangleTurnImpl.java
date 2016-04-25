package model.classes;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.LastMove;

public class TriangleTurnImpl extends TurnImpl {

    public TriangleTurnImpl(Integer rowsNumber, Integer columnNumber) {
        super(rowsNumber, columnNumber);
    }

    @Override
    public boolean isEnded() {

        if (!isStarted()) {
            throw new IllegalStateException("the match can't be ended if it isn't even started");
        }

        return getPlayerPoints(GridOption.PLAYER1) + getPlayerPoints(
                GridOption.PLAYER2) == ((grid.getHorizontalListSize() - 1) * (grid.getVerticallListSize() - 1)) * 2
                        ? true : false;
    }

    @Override
    public void setLine(final ListType list, final Integer listIndex, final Integer position) {

        if (list.equals(ListType.DIAGONAL)) {
            grid.setVerticalLine(listIndex, position, this.turn);
            if (verticalPointScored(listIndex, position) > 0) {
                addPoints(verticalPointScored(listIndex, position));
            } else {
                nextTurn();
            }

            LastMove lastMove = new LastMoveImpl();
            lastMove.setLastListType(list);
            lastMove.setLastListIndex(listIndex);
            lastMove.setLastPosition(position);
            lastMoveList.add(lastMove);
        } else {
            super.setLine(list, listIndex, position);
        }
    }

    private Integer horizontalPointScored(final int listIndex, final int position) {

    }

    private Integer verticalPointScored(final int listIndex, final int position) {

    }

    private Integer diagonalPointScored(final int listIndex, final int position) {

    }

    @Override
    public void undoLastMove() {

    }
}
