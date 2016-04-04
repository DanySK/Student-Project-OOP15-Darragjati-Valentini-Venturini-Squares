package model.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import model.enumerations.GridOption;

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

        BaseGridImpl gridOfSize = new BaseGridImpl(SIZE, SIZE);

        assertEquals(gridOfSize.getTotalMoves(), gridOfSize.getRemainingMoves()); // verifies
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
                                             // the list is initialized as
                                             // Gridoption.EMPTY
            for (int z = 0; z < SIZE; z++) {
                assertEquals(gridOfSize.getCopyOfElement(i, z), GridOption.EMPTY);
            }
        }
        // non effettuo il controllo per le verticali AGGIUNGERE

        assertFalse(gridOfSize.isStarted());
        assertFalse(gridOfSize.isEnded());

        gridOfSize.startMatch();
        assertTrue(gridOfSize.isStarted());
        gridOfSize.setLine(0, 0);

        assertEquals(gridOfSize.getRemainingMoves(), (Integer) (gridOfSize.getTotalMoves() - 1));

        gridOfSize.setLine(7, 0);
        gridOfSize.setLine(1, 0);

        System.out.print("Oriz ");
        for (int i = 0; i < SIZE + 1; i++) {
            for (int z = 0; z < SIZE; z++) {
                System.out.print(gridOfSize.getCopyOfElement(i, z) + "|");
            }
        }
        System.out.print("\nVert ");
        for (int i = SIZE + 1; i < 2 * (SIZE + 1); i++) {
            for (int z = 0; z < SIZE; z++) {
                System.out.print(gridOfSize.getCopyOfElement(i, z) + "|");
            }
        }
        System.out.println();

        GridOption player = gridOfSize.getCurrentPlayerTurn();
        gridOfSize.setLine(8, 0);

        assertEquals(gridOfSize.getRemainingMoves(), (Integer) (gridOfSize.getTotalMoves() - 4));
        assertNotEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertEquals(player, gridOfSize.getCurrentPlayerTurn()); // verifies if
                                                                 // the player
                                                                 // has received
                                                                 // a bonus move

        BaseGridImpl gridOfSize2 = new BaseGridImpl(SIZE, SIZE);

        gridOfSize2.startMatch();

        for (int i = 0; i < 2 * (SIZE + 1); i++) {
            for (int z = 0; z < SIZE; z++) {
                gridOfSize2.setLine(i, z);
            }
        }

        assertTrue(gridOfSize2.getRemainingMoves().equals(0));
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

        BaseGridImpl testGrid;

        try {
            testGrid = new BaseGridImpl(SIZE - 4, SIZE - 4);
            fail("Can't create a grid too small");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        try {
            testGrid = new BaseGridImpl(SIZE + SIZE, SIZE + SIZE);
            fail("Can't create a grid too big");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }

        testGrid = new BaseGridImpl(SIZE, SIZE);
        try {
            testGrid.setLine(0, 0);
            fail("Can't insert a move when the match isn't started");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        testGrid.startMatch();
        try {
            testGrid.setLine(-1, -1);
            fail("Can't insert those parameters");
        } catch (IllegalArgumentException e) { // forse potrebbe essere anche un
                                               // index out of bound
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        try {
            testGrid.setLine(0, 7);
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
