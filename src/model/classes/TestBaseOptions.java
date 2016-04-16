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
 * 
 * 
 *
 */
public class TestBaseOptions {

    private static final Integer SIZE = 6;

    /**
     * 
     */
    @Test
    public void test() {

        Turn gridOfSize = new TurnImpl(SIZE, SIZE);

        assertEquals(gridOfSize.getGrid().getTotalMoves(), gridOfSize.getGrid().getRemainingMoves()); // verifies
        // that
        // total
        // moves
        // are
        // the
        // same
        // as
        // remaining
        // moves

        for (int i = 0; i < SIZE + 1; i++) { // verifies that every element in
            // the list is initialized as EMPTY
            for (int z = 0; z < SIZE; z++) {
                assertEquals(gridOfSize.getGrid().getCopyOfHorizontalElement(i, z), GridOption.EMPTY);
                assertEquals(gridOfSize.getGrid().getCopyOfVerticalElement(i, z), GridOption.EMPTY);
            }
        }

        assertFalse(gridOfSize.isStarted());

        gridOfSize.startMatch();
        assertTrue(gridOfSize.isStarted());
        gridOfSize.setLine(ListType.VERTICAL, 0, 0);

        assertEquals(gridOfSize.getGrid().getRemainingMoves(), (Integer) (gridOfSize.getGrid().getTotalMoves() - 1));

        gridOfSize.setLine(ListType.HORIZONTAL, 0, 0);
        gridOfSize.setLine(ListType.HORIZONTAL, 1, 0);

        GridOption player = gridOfSize.getGrid().getCurrentPlayerTurn();
        gridOfSize.setLine(ListType.VERTICAL, 1, 0);

        assertEquals(gridOfSize.getGrid().getRemainingMoves(), (Integer) (gridOfSize.getGrid().getTotalMoves() - 4));
        assertNotEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertEquals(player, gridOfSize.getGrid().getCurrentPlayerTurn()); // verifies
                                                                           // if
        // the player
        // has received
        // a bonus move

        Turn gridOfSize2 = new TurnImpl(SIZE, SIZE);

        gridOfSize2.startMatch();

        for (int i = 0; i < SIZE + 1; i++) {
            for (int z = 0; z < SIZE; z++) {
                gridOfSize2.setLine(ListType.HORIZONTAL, i, z);
                gridOfSize2.setLine(ListType.VERTICAL, i, z);
            }
        }

        assertTrue(gridOfSize2.getGrid().getRemainingMoves().equals(0));
        System.out.println("Player1 " + gridOfSize2.getPlayerPoints(GridOption.PLAYER1) + " Player2 "
                + gridOfSize2.getPlayerPoints(GridOption.PLAYER2));
        assertTrue(gridOfSize2.isEnded());
        assertNotEquals(GridOption.EMPTY, gridOfSize2.getWinner());
    }

    /**
     * 
     */
    @Test
    public void testExceptions() {

        Turn testGrid;

        try {
            testGrid = new TurnImpl(SIZE - 4, SIZE - 4);
            fail("Can't create a grid too small");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        try {
            testGrid = new TurnImpl(SIZE + SIZE, SIZE + SIZE);
            fail("Can't create a grid too big");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }

        testGrid = new TurnImpl(SIZE, SIZE);
        try{
            testGrid.isEnded();
            fail("the match can't be ended if it isn't started");
        } catch (IllegalStateException e){
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        try {
            testGrid.setLine(ListType.HORIZONTAL, 0, 0);
            fail("Can't insert a move when the match isn't started");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        testGrid.startMatch();
        try {
            testGrid.setLine(ListType.HORIZONTAL, -1, -1);
            fail("Can't insert those parameters");
        } catch (IllegalArgumentException e) { // forse potrebbe essere anche un
                                               // index out of bound
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        try {
            testGrid.setLine(ListType.HORIZONTAL, 0, 7);
            fail("The grid isn't big enough");
        } catch (IndexOutOfBoundsException e) { // forse potrebbe essere anche
                                                // un
                                                // illegal argument
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        try {
            testGrid.getWinner();
            fail("");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
    }

}
