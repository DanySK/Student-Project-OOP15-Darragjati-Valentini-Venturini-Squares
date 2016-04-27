package model.classes;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.LastMove;
import model.interfaces.TriangleGrid;

public class TriangleTurnImpl extends TurnImpl {

    private TriangleGrid squareGrid;

    public TriangleTurnImpl(Integer rowsNumber, Integer columnsNumber) {
        super(rowsNumber, columnsNumber);
        squareGrid = new TriangleGridImpl(rowsNumber, columnsNumber);
    }

    @Override
    public boolean isEnded() {
        if (!isStarted()) {
            throw new IllegalStateException("the match can't be ended if it isn't even started");
        }
        return getPlayerPoints(GridOption.PLAYER1)
                + getPlayerPoints(GridOption.PLAYER2) == ((squareGrid.getHorizontalListSize() - 1)
                        * (squareGrid.getVerticallListSize() - 1)) * 2 ? true : false;
    }

    @Override
    public void setLine(final ListType list, final Integer listIndex, final Integer position) {

        if (list.equals(ListType.DIAGONAL)) {
            squareGrid.setVerticalLine(listIndex, position, this.turn);
            if (diagonalPointScored(listIndex, position) > 0) {
                addPoints(diagonalPointScored(listIndex, position));
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
        for (int i = 0; i < 7; i++) {
            for (int z = 0; z < 6; z++) {
                System.out.println("Orizzontale(" + i + ", " + z + "): " + squareGrid.getCopyOfHorizontalElement(i, z));
                System.out.println("Verticale(" + i + ", " + z + "): "  + squareGrid.getCopyOfVerticalElement(i, z));
                System.out.println("Diagonale(" + i + ", " + z + "): " + squareGrid.getCopyOfDiagonalElement(i, z));
            }
        }
    }

    protected Integer horizontalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex > 0) {
            if (!squareGrid.getCopyOfVerticalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                    && !squareGrid.getCopyOfDiagonalElement(listIndex - 1, position).equals(GridOption.EMPTY)) {
                points++;
            }
        }

        if (listIndex < squareGrid.getDiagonalListSize() - 1) {
            if (!squareGrid.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)
                    && !squareGrid.getCopyOfDiagonalElement(listIndex, position).equals(GridOption.EMPTY)) {
                points++;
            }
        }
        return points;
    }

    protected Integer verticalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (listIndex > 0) {
            if (!squareGrid.getCopyOfHorizontalElement(position, listIndex - 1).equals(GridOption.EMPTY)
                    && !squareGrid.getCopyOfDiagonalElement(position, listIndex - 1).equals(GridOption.EMPTY)) {
                points++;
            }
        }

        if (listIndex < squareGrid.getVerticallListSize() - 1) {
            if (squareGrid.getCopyOfHorizontalElement(position + 1, listIndex) != GridOption.EMPTY
                    && !squareGrid.getCopyOfDiagonalElement(position, listIndex).equals(GridOption.EMPTY)) {
                points++;
            }
        }
        return points;
    }

    private Integer diagonalPointScored(final int listIndex, final int position) {

        int points = 0;

        if (!squareGrid.getCopyOfHorizontalElement(listIndex, position).equals(GridOption.EMPTY)
                && squareGrid.getCopyOfVerticalElement(position + 1, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }

        if (!squareGrid.getCopyOfHorizontalElement(listIndex + 1, position).equals(GridOption.EMPTY)
                && squareGrid.getCopyOfVerticalElement(position, listIndex).equals(GridOption.EMPTY)) {
            points++;
        }
        return points;
    }

    @Override
    public void undoLastMove() {

    }

    @Override
    public BaseGrid getCopyOfGrid() {
        TriangleGrid copyOfTriangleGrid = this.squareGrid;
        return copyOfTriangleGrid;
    }
}
