package model.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.TriangleGrid;
import model.interfaces.Turn;

/**
 * This class simulates the possible moves of a game. The games rules are
 * modified by using the TriangleGridImpl object.
 */
public class TestTriangleGrid {

    private static final Integer STANDARD_SIZE = 6;
    private static final Integer HORIZONTAL_SIZE = 5;
    private static final Integer VERTICAL_SIZE = 4;

    /**
     * Tests the methods of TriangleGridImpl and TurnImpl.
     */
    @Test
    public void test() {

        TriangleGrid triangleGrid = new TriangleGridImpl(HORIZONTAL_SIZE, VERTICAL_SIZE);
        Turn gridOfSize = new TurnImpl(triangleGrid);

        assertEquals(triangleGrid.getTotalMoves(), triangleGrid.getRemainingMoves());

        // verifies that every element in the list is initialized as EMPTY
        for (int i = 0; i < HORIZONTAL_SIZE + 1; i++) {
            for (int z = 0; z < HORIZONTAL_SIZE; z++) {
                assertEquals(triangleGrid.getCopyOfHorizontalElement(i, z), GridOption.EMPTY);
            }
        }
        for (int i = 0; i < VERTICAL_SIZE + 1; i++) {
            for (int z = 0; z < VERTICAL_SIZE; z++) {
                assertEquals(triangleGrid.getCopyOfVerticalElement(i, z), GridOption.EMPTY);
            }
        }
        for (int i = 0; i < HORIZONTAL_SIZE; i++) {
            for (int z = 0; z < VERTICAL_SIZE; z++) {
                assertEquals(triangleGrid.getCopyOfDiagonalElement(i, z), GridOption.EMPTY);
            }
        }

        assertFalse(gridOfSize.isStarted());
        gridOfSize.startMatch();
        assertTrue(gridOfSize.isStarted());
        gridOfSize.setLine(ListType.VERTICAL, 0, 0);
        assertEquals(triangleGrid.getRemainingMoves(), (Integer) (triangleGrid.getTotalMoves() - 1));
        assertEquals(gridOfSize.getCopyOfLastMove().getLastListType(), ListType.VERTICAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getLastListIndex(), (Integer) 0);
        assertEquals(gridOfSize.getCopyOfLastMove().getLastPosition(), (Integer) 0);

        gridOfSize.setLine(ListType.HORIZONTAL, 0, 0);
        gridOfSize.setLine(ListType.HORIZONTAL, 1, 0);

        GridOption player = gridOfSize.getCurrentPlayerTurn();
        gridOfSize.setLine(ListType.VERTICAL, 1, 0);

        assertEquals(triangleGrid.getRemainingMoves(), (Integer) (triangleGrid.getTotalMoves() - 4));
        // the player points should be the same with this game mode
        assertEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertNotEquals(player, gridOfSize.getCurrentPlayerTurn());

        player = gridOfSize.getCurrentPlayerTurn();
        gridOfSize.setLine(ListType.DIAGONAL, 0, 0);
        assertNotEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertEquals(player, gridOfSize.getCurrentPlayerTurn());
        assertEquals(gridOfSize.getCopyOfLastMove().getLastListType(), ListType.DIAGONAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getLastListIndex(), (Integer) 0);
        assertEquals(gridOfSize.getCopyOfLastMove().getLastPosition(), (Integer) 0);

        gridOfSize.undoLastMove();
        assertEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertEquals(player, gridOfSize.getCurrentPlayerTurn());
        assertEquals(gridOfSize.getCopyOfLastMove().getLastListType(), ListType.VERTICAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getLastListIndex(), (Integer) 1);
        assertEquals(gridOfSize.getCopyOfLastMove().getLastPosition(), (Integer) 0);
        gridOfSize.undoLastMove();
        assertNotEquals(player, gridOfSize.getCurrentPlayerTurn());

        BaseGrid squareGrid2 = new TriangleGridImpl(STANDARD_SIZE, STANDARD_SIZE);
        Turn gridOfSize2 = new TurnImpl(squareGrid2);

        gridOfSize2.startMatch();
        // fills the grid with all thepossible moves
        for (int i = 0; i < STANDARD_SIZE + 1; i++) {
            for (int z = 0; z < STANDARD_SIZE; z++) {
                gridOfSize2.setLine(ListType.HORIZONTAL, i, z);
                gridOfSize2.setLine(ListType.VERTICAL, i, z);
            }
        }
        for (int i = 0; i < STANDARD_SIZE; i++) {
            for (int z = 0; z < STANDARD_SIZE; z++) {
                gridOfSize2.setLine(ListType.DIAGONAL, i, z);
            }
        }

        assertTrue(squareGrid2.getRemainingMoves().equals(0));
        assertNotEquals(gridOfSize2.getPlayerPoints(GridOption.PLAYER1),
                gridOfSize2.getPlayerPoints(GridOption.PLAYER2));
        assertTrue(gridOfSize2.isEnded());
        assertNotEquals(GridOption.EMPTY, gridOfSize2.getWinner());
    }

    @Test
    public void testExceptions() {

    }
}
