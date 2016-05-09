package model.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import model.enumerations.GridOption;
import model.enumerations.ListType;
import model.interfaces.BaseGrid;
import model.interfaces.Turn;

/**
 * This class simulates the possible moves of a game.
 */
public class TestBaseOptions {

    private static final Integer STANDARD_SIZE = 6;
    private static final Integer HORIZONTAL_SIZE = 5;
    private static final Integer VERTICAL_SIZE = 4;
    private static final String ERROR = "Wrong exception thrown";

    /**
     * Tests the methods of BaseGridImpl and TurnImpl.
     */
    @Test
    public void test() {

        final BaseGrid squareGrid = new BaseGridImpl(HORIZONTAL_SIZE, VERTICAL_SIZE);
        final Turn gridOfSize = new GameImpl(squareGrid);

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
        gridOfSize.setLine(ListType.VERTICAL, 0, 0);
        assertEquals(squareGrid.getRemainingMoves(), (Integer) (squareGrid.getTotalMoves() - 1));
        assertEquals(gridOfSize.getCopyOfLastMove().getLastListType(), ListType.VERTICAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getLastListIndex(), (Integer) 0);
        assertEquals(gridOfSize.getCopyOfLastMove().getLastPosition(), (Integer) 0);

        gridOfSize.setLine(ListType.HORIZONTAL, 0, 0);
        gridOfSize.setLine(ListType.HORIZONTAL, 1, 0);

        final GridOption player = gridOfSize.getCopyOfCurrentPlayerTurn();
        gridOfSize.setLine(ListType.VERTICAL, 1, 0);

        assertEquals(squareGrid.getRemainingMoves(), (Integer) (squareGrid.getTotalMoves() - 4));
        assertNotEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        // verifies if the player has received a bonus move
        assertEquals(player, gridOfSize.getCopyOfCurrentPlayerTurn());
        gridOfSize.undoLastMove();
        assertEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertEquals(player, gridOfSize.getCopyOfCurrentPlayerTurn());
        assertEquals(gridOfSize.getCopyOfLastMove().getLastListType(), ListType.HORIZONTAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getLastListIndex(), (Integer) 1);
        assertEquals(gridOfSize.getCopyOfLastMove().getLastPosition(), (Integer) 0);
        gridOfSize.undoLastMove();
        assertNotEquals(player, gridOfSize.getCopyOfCurrentPlayerTurn());

        final BaseGrid squareGrid2 = new BaseGridImpl(STANDARD_SIZE, STANDARD_SIZE);
        final Turn gridOfSize2 = new GameImpl(squareGrid2);

        gridOfSize2.startMatch();

        // fills the grid with all the possible moves
        for (int i = 0; i < STANDARD_SIZE + 1; i++) {
            for (int z = 0; z < STANDARD_SIZE; z++) {
                gridOfSize2.setLine(ListType.HORIZONTAL, i, z);
                gridOfSize2.setLine(ListType.VERTICAL, i, z);
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
    public void testExceptions() {

        BaseGrid exceptionGrid;
        Turn exceptionGame;
        
        try {
            exceptionGrid = new BaseGridImpl(STANDARD_SIZE - 4, STANDARD_SIZE - 4);
            fail("Can't create a grid too small");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGrid = new BaseGridImpl(STANDARD_SIZE + STANDARD_SIZE, STANDARD_SIZE + STANDARD_SIZE);
            fail("Can't create a grid too big");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail(ERROR);
        }

        exceptionGrid = new BaseGridImpl(STANDARD_SIZE, STANDARD_SIZE);
        exceptionGame = new GameImpl(exceptionGrid);
        try {
            exceptionGame.isEnded();
            fail("the match can't be ended if it isn't started");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGame.setLine(ListType.HORIZONTAL, 0, 0);
            fail("Can't insert a move when the match isn't started");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        exceptionGame.startMatch();
        try {
            exceptionGame.setLine(ListType.HORIZONTAL, -1, -1);
            fail("Can't insert those parameters");
        } catch (IllegalArgumentException e) { // forse potrebbe essere anche un
                                               // index out of bound
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            // CHECKSTYLE:OFF:
            exceptionGame.setLine(ListType.HORIZONTAL, 0, 7);
            // CHECKSTYLE:ON:
            fail("The grid isn't big enough");
        } catch (IndexOutOfBoundsException e) { // forse potrebbe essere anche
                                                // un
                                                // illegal argument
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGame.undoLastMove();
            fail("You can't undo a move if a player didn't do at least one"); // riformulare
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGame.setLine(ListType.DIAGONAL, 0, 0);
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
