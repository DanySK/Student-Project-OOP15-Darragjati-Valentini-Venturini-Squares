package model.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.SquareGrid;
import model.interfaces.Game;
import model.interfaces.Move;

/**
 * This class simulates the possible moves of a game.
 */
public class TestSquareGrid {

    private static final Integer STANDARD_SIZE = 6;
    private static final Integer HORIZONTAL_SIZE = 5;
    private static final Integer VERTICAL_SIZE = 4;
    private static final String ERROR = "Wrong exception thrown";
    private final Move move = new MoveImpl();

    /**
     * Tests the methods of BaseGridImpl and TurnImpl.
     */
    @Test
    public void test() {

        final SquareGrid squareGrid = new SquareGridImpl(HORIZONTAL_SIZE, VERTICAL_SIZE);
        final Game gridOfSize = new GameImpl(squareGrid);

        // verifies that total moves are the same as remaining moves
        assertEquals(squareGrid.getTotalMoves(), squareGrid.getRemainingMoves());

        // verifies that every element in the lists is initialized as EMPTY
        for (int i = 0; i < HORIZONTAL_SIZE + 1; i++) {
            for (int z = 0; z < HORIZONTAL_SIZE; z++) {
                assertEquals(squareGrid.getCopyOfHorizontalElement(i, z), GridOption.EMPTY);
            }
        }
        for (int i = 0; i < VERTICAL_SIZE + 1; i++) {
            for (int z = 0; z < VERTICAL_SIZE; z++) {
                assertEquals(squareGrid.getCopyOfVerticalElement(i, z), GridOption.EMPTY);
            }
        }
        assertFalse(gridOfSize.isStarted());
        gridOfSize.startMatch();
        assertTrue(gridOfSize.isStarted());
        move.setListType(ListType.VERTICAL);
        move.setListIndex(0);
        move.setPosition(0);
        gridOfSize.setLine(move);
        assertEquals(squareGrid.getRemainingMoves(), (Integer) (squareGrid.getTotalMoves() - 1));
        assertEquals(gridOfSize.getCopyOfLastMove().getListType(), ListType.VERTICAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getListIndex(), (Integer) 0);
        assertEquals(gridOfSize.getCopyOfLastMove().getPosition(), (Integer) 0);
        move.setListType(ListType.HORIZONTAL);
        move.setListIndex(0);
        move.setPosition(0);
        gridOfSize.setLine(move);
        move.setListType(ListType.HORIZONTAL);
        move.setListIndex(1);
        move.setPosition(0);
        gridOfSize.setLine(move);
        move.setListType(ListType.VERTICAL);
        // this turn memorization is used later to check if the turn switch is
        // correctly implemented
        final GridOption player = gridOfSize.getCopyOfCurrentPlayerTurn();
        move.setListIndex(1);
        move.setPosition(0);
        gridOfSize.setLine(move);
        assertEquals(squareGrid.getRemainingMoves(), (Integer) (squareGrid.getTotalMoves() - 4));
        assertNotEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        // verifies if the player has received a bonus move
        assertEquals(player, gridOfSize.getCopyOfCurrentPlayerTurn());

        gridOfSize.undoLastMove();
        assertEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertEquals(player, gridOfSize.getCopyOfCurrentPlayerTurn());
        assertEquals(gridOfSize.getCopyOfLastMove().getListType(), ListType.HORIZONTAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getListIndex(), (Integer) 1);
        assertEquals(gridOfSize.getCopyOfLastMove().getPosition(), (Integer) 0);
        gridOfSize.undoLastMove();
        assertNotEquals(player, gridOfSize.getCopyOfCurrentPlayerTurn());

        final SquareGrid squareGrid2 = new SquareGridImpl(STANDARD_SIZE, STANDARD_SIZE);
        final Game gridOfSize2 = new GameImpl(squareGrid2);
        gridOfSize2.startMatch();
        // fills the grid with all the possible moves
        for (int i = 0; i < STANDARD_SIZE + 1; i++) {
            for (int z = 0; z < STANDARD_SIZE; z++) {
                move.setListType(ListType.HORIZONTAL);
                move.setListIndex(i);
                move.setPosition(z);
                gridOfSize2.setLine(move);
                move.setListType(ListType.VERTICAL);
                gridOfSize2.setLine(move);
            }
        }
        assertTrue(squareGrid2.getRemainingMoves().equals(0));
        assertNotEquals(gridOfSize2.getPlayerPoints(GridOption.PLAYER1),
                gridOfSize2.getPlayerPoints(GridOption.PLAYER2));
        assertTrue(gridOfSize2.isEnded());
        assertNotEquals(GridOption.EMPTY, gridOfSize2.getWinner());
    }

    /**
     * Test if the methods of BaseGridImpl and TurnImpl throws the correct
     * exceptions.
     */
    @Test
    // CHECKSTYLE:OFF:
    public void testExceptions() {

        SquareGrid exceptionGrid;
        Game exceptionGame;

        try {
            exceptionGrid = new SquareGridImpl(STANDARD_SIZE - 4, STANDARD_SIZE - 4);
            fail("Can't create a grid too small");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGrid = new SquareGridImpl(STANDARD_SIZE + STANDARD_SIZE, STANDARD_SIZE + STANDARD_SIZE);
            fail("Can't create a grid too big");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail(ERROR);
        }

        exceptionGrid = new SquareGridImpl(STANDARD_SIZE, STANDARD_SIZE);
        exceptionGame = new GameImpl(exceptionGrid);
        try {
            exceptionGame.isEnded();
            fail("the match can't be ended if it isn't started");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGame.getCopyOfCurrentPlayerTurn();
            fail("you can't get the player turn if the match isn't started");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            move.setListType(ListType.HORIZONTAL);
            move.setListIndex(0);
            move.setPosition(0);
            exceptionGame.setLine(move);
            fail("Can't insert a move when the match isn't started");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        exceptionGame.startMatch();
        try {
            move.setListType(ListType.HORIZONTAL);
            move.setListIndex(-1);
            move.setPosition(-1);
            exceptionGame.setLine(move);
            fail("Can't insert those parameters");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            move.setListType(ListType.HORIZONTAL);
            move.setListIndex(0);
            // CHECKSTYLE:OFF:
            move.setPosition(7);
            // CHECKSTYLE:ON:
            exceptionGame.setLine(move);
            fail("The grid isn't big enough");
        } catch (IndexOutOfBoundsException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGame.undoLastMove();
            fail("You can't undo a move if noone of the two players have set one");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGame.getCopyOfLastMove();
            fail("You can't get the last move if noone of the two players have set one");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            move.setListType(ListType.DIAGONAL);
            move.setListIndex(0);
            move.setPosition(0);
            exceptionGame.setLine(move);
            fail("You can't set a diagonal line, the base grid doesn't include this option");
        } catch (UnsupportedOperationException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGame.getWinner();
            fail("");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
    }
}
