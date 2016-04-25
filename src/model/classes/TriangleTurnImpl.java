package model.classes;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.LastMove;

public class TriangleTurnImpl extends TurnImpl {

    private TriangleGridImpl grid;
    
    public TriangleTurnImpl(Integer rowsNumber, Integer columnNumber) {
        super(rowsNumber, columnNumber);
        grid = new TriangleGridImpl(rowsNumber, columnNumber);
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
        
        int points = 0;

        if (listIndex > 0) {
            if(grid.ge)
            if (grid.getCopyOfHorizontalElement(listIndex - 1, position) != GridOption.EMPTY) {
                if (grid.getCopyOfVerticalElement(position, listIndex - 1) != GridOption.EMPTY
                        && grid.getCopyOfVerticalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
                    points++;
                }
            }
        }

        if (listIndex < grid.getHorizontalListSize() - 1) {
            if (grid.getCopyOfHorizontalElement(listIndex + 1, position) != GridOption.EMPTY) {
                if (grid.getCopyOfVerticalElement(position, listIndex) != GridOption.EMPTY
                        && grid.getCopyOfVerticalElement(position + 1, listIndex) != GridOption.EMPTY) {
                    points++;
                }
            }
        }
        return points;
    }

    private Integer verticalPointScored(final int listIndex, final int position) {

    }

    private Integer diagonalPointScored(final int listIndex, final int position) {

    }

    @Override
    public void undoLastMove() {

    }
}
