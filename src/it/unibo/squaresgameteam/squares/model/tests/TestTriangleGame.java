package it.unibo.squaresgameteam.squares.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.squaresgameteam.squares.model.classes.GameImpl;
import it.unibo.squaresgameteam.squares.model.classes.MoveImpl;
import it.unibo.squaresgameteam.squares.model.classes.SquareGridImpl;
import it.unibo.squaresgameteam.squares.model.classes.TriangleGridImpl;
import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.enumerations.ListType;
import it.unibo.squaresgameteam.squares.model.exceptions.UnsupportedSizeException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.SquareGrid;
import it.unibo.squaresgameteam.squares.model.interfaces.BaseGrid;
import it.unibo.squaresgameteam.squares.model.interfaces.Game;
import it.unibo.squaresgameteam.squares.model.interfaces.Move;

/**
 * This class simulates the possible moves of a game. The games rules are
 * modified by using the TriangleGridImpl object.
 */
public class TestTriangleGame {

    private static final Integer STANDARD_SIZE = 6;
    private static final Integer HORIZONTAL_SIZE = 5;
    private static final Integer VERTICAL_SIZE = 4;
    private Move move;

    /**
     * Tests the basic move option offered by a triangle game.
     * 
     * @throws UnsupportedSizeException
     *             if the list chosen is not supported by the game mode
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    @Test
    public void test() throws UnsupportedSizeException, UnexistentLineListException {

        final BaseGrid triangleGrid = new TriangleGridImpl(HORIZONTAL_SIZE, VERTICAL_SIZE);
        final Game gridOfSize = new GameImpl(triangleGrid, "Rei Ayanami", "Shinji Ikari");

        assertEquals(triangleGrid.getTotalMoves(), triangleGrid.getRemainingMoves());

        // verifies that every element in the list is initialized as EMPTY
        for (int i = 0; i < HORIZONTAL_SIZE + 1; i++) {
            for (int z = 0; z < HORIZONTAL_SIZE; z++) {
                assertEquals(((SquareGridImpl) triangleGrid).getHorizontalLinePlayer(i, z), GridOption.EMPTY);
            }
        }
        for (int i = 0; i < VERTICAL_SIZE + 1; i++) {
            for (int z = 0; z < VERTICAL_SIZE; z++) {
                assertEquals(((SquareGridImpl) triangleGrid).getVerticalLinePlayer(i, z), GridOption.EMPTY);
            }
        }
        for (int i = 0; i < HORIZONTAL_SIZE; i++) {
            for (int z = 0; z < VERTICAL_SIZE; z++) {
                assertEquals(((TriangleGridImpl) triangleGrid).getDiagonalLinePlayer(i, z), GridOption.EMPTY);
            }
        }
        assertSame(VERTICAL_SIZE * HORIZONTAL_SIZE * 2, triangleGrid.getMatchMaximumPoints());
        assertFalse(gridOfSize.isStarted());
        gridOfSize.startMatch();
        assertTrue(gridOfSize.isStarted());
        move = new MoveImpl(ListType.VERTICAL, 0, 0);
        gridOfSize.setLine(move);
        assertEquals(triangleGrid.getRemainingMoves(), (Integer) (triangleGrid.getTotalMoves() - 1));
        assertEquals(gridOfSize.getCopyOfLastMove().getListType(), ListType.VERTICAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getListIndex(), (Integer) 0);
        assertEquals(gridOfSize.getCopyOfLastMove().getPosition(), (Integer) 0);
        move = new MoveImpl(ListType.HORIZONTAL, 0, 0);
        gridOfSize.setLine(move);
        move = new MoveImpl(ListType.HORIZONTAL, 1, 0);
        gridOfSize.setLine(move);
        // this player turn memorization is used later to check if the turn
        // switch is correctly implemented
        GridOption player = gridOfSize.getCurrentPlayerTurn();
        move = new MoveImpl(ListType.VERTICAL, 1, 0);
        gridOfSize.setLine(move);
        assertEquals(triangleGrid.getRemainingMoves(), (Integer) (triangleGrid.getTotalMoves() - 4));
        // the player points should be the same with this game mode
        assertEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertNotEquals(player, gridOfSize.getCurrentPlayerTurn());
        player = gridOfSize.getCurrentPlayerTurn();
        move = new MoveImpl(ListType.DIAGONAL, 0, 0);
        gridOfSize.setLine(move);
        assertNotEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertEquals(player, gridOfSize.getCurrentPlayerTurn());
        assertEquals(gridOfSize.getCopyOfLastMove().getListType(), ListType.DIAGONAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getListIndex(), (Integer) 0);
        assertEquals(gridOfSize.getCopyOfLastMove().getPosition(), (Integer) 0);

        // test if the undo is working correctly
        gridOfSize.undoLastMove();
        assertEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertEquals(player, gridOfSize.getCurrentPlayerTurn());
        assertEquals(gridOfSize.getCopyOfLastMove().getListType(), ListType.VERTICAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getListIndex(), (Integer) 1);
        assertEquals(gridOfSize.getCopyOfLastMove().getPosition(), (Integer) 0);
        // verifies that the player points are now the same
        assertEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        gridOfSize.undoLastMove();
        assertNotEquals(player, gridOfSize.getCurrentPlayerTurn());

        // check if the other methods of points assignaments work correctly
        move = new MoveImpl(ListType.DIAGONAL, 1, 1);
        gridOfSize.setLine(move);
        move = new MoveImpl(ListType.HORIZONTAL, 2, 1);
        gridOfSize.setLine(move);
        move = new MoveImpl(ListType.VERTICAL, 1, 1);
        gridOfSize.setLine(move);
        move.setListType(ListType.VERTICAL);
        move.setListIndex(2);
        move.setPosition(1);
        gridOfSize.setLine(move);
        move = new MoveImpl(ListType.HORIZONTAL, 1, 1);
        gridOfSize.setLine(move);
        assertEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
    }

    /**
     * Tests a complete match.
     * 
     * @throws UnsupportedSizeException
     *             if the list chosen is not supported by the game mode
     * @throws UnexistentLineListException
     *             if the listIndex input is not correctF
     */
    public void testCompleteMatch() throws UnsupportedSizeException, UnexistentLineListException {
        final SquareGrid squareGrid2 = new TriangleGridImpl(STANDARD_SIZE, STANDARD_SIZE);
        final Game gridOfSize2 = new GameImpl(squareGrid2, "Rei Ayanami", "Shinji Ikari");
        gridOfSize2.startMatch();
        // fills the grid with all thepossible moves
        for (int i = 0; i < STANDARD_SIZE + 1; i++) {
            for (int z = 0; z < STANDARD_SIZE; z++) {
                move.setListIndex(i);
                move.setPosition(z);
                move.setListType(ListType.HORIZONTAL);
                gridOfSize2.setLine(move);
                move.setListType(ListType.VERTICAL);
                gridOfSize2.setLine(move);
            }
        }
        for (int i = 0; i < STANDARD_SIZE; i++) {
            for (int z = 0; z < STANDARD_SIZE; z++) {
                move.setListType(ListType.DIAGONAL);
                move.setListIndex(i);
                move.setPosition(z);
                gridOfSize2.setLine(move);
            }
        }
        assertTrue(squareGrid2.getRemainingMoves().equals(0));
        assertNotEquals(gridOfSize2.getPlayerPoints(GridOption.PLAYER1),
                gridOfSize2.getPlayerPoints(GridOption.PLAYER2));
        assertTrue(gridOfSize2.isEnded());
        assertNotEquals(GridOption.EMPTY, gridOfSize2.getWinner());
        assertNotEquals(gridOfSize2.getMatchDuration(), -1.0);
    }
}
