package model.classes;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.LastMove;

public class TriangleTurnImpl extends TurnImpl {

    private TriangleGridImpl triangleGrid;

    public TriangleTurnImpl(Integer rowsNumber, Integer columnNumber) {
        super(rowsNumber, columnNumber); // creo una griglia di quadrati che non
                                         // devo usare!
        triangleGrid = new TriangleGridImpl(rowsNumber, columnNumber);
    }

    @Override
    public boolean isEnded() {

        if (!isStarted()) {
            throw new IllegalStateException("the match can't be ended if it isn't even started");
        }

        return getPlayerPoints(GridOption.PLAYER1)
                + getPlayerPoints(GridOption.PLAYER2) == ((triangleGrid.getHorizontalListSize() - 1)
                        * (triangleGrid.getVerticallListSize() - 1)) * 2 ? true : false;
    }

    @Override
    public void setLine(final ListType list, final Integer listIndex, final Integer position) {

        if (list.equals(ListType.DIAGONAL)) {
            triangleGrid.setVerticalLine(listIndex, position, this.turn);
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
            if (!triangleGrid.getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                    && !triangleGrid.getCopyOfDiagonalElement(listIndex - 1, position).equals(GridOption.EMPTY)) {
                points++;
            }
        }

        if (listIndex < triangleGrid.getDiagonalListSize() - 1) {
            if (!triangleGrid.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                    && !triangleGrid.getCopyOfDiagonalElement(listIndex, position).equals(GridOption.EMPTY)) {
                points++;
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
