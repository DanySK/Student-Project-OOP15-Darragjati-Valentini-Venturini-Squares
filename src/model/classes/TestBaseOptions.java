package model.classes;

import static org.junit.Assert.*;

import org.junit.Test;

import model.enumerations.GridOption;

public class TestBaseOptions {

    private static final Integer SIZE = 6;
    
    @Test
    public void test() {
	
	

	BaseGridImpl gridOfSize = new BaseGridImpl(SIZE);

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
					     // the list is initialized as EMPTY
	    for (int z = 0; z < SIZE; z++) {
		assertEquals(gridOfSize.getHorizontalElement(i, z), GridOption.EMPTY);
		assertEquals(gridOfSize.getCopyOfElement(i, z), GridOption.EMPTY);
	    }
	}

	assertFalse(gridOfSize.isStarted());
	assertFalse(gridOfSize.isEnded());

	gridOfSize.startMatch();
	assertTrue(gridOfSize.isStarted());
	gridOfSize.setLine(0, 0);

	assertEquals(gridOfSize.getRemainingMoves(), (Integer) (gridOfSize.getTotalMoves() - 1));

	System.out.print("Orizzontale ");
	for (int i = 0; i < SIZE + 1; i++) { // verifies that every element in
					     // the list is initialized as EMPTY
	    for (int z = 0; z < SIZE; z++) {
		System.out.print(gridOfSize.getHorizontalElement(i, z) + "|");
	    }
	}
	System.out.print("\nVerticale ");
	for (int i = 0; i < SIZE + 1; i++) { // verifies that every element in
					     // the list is initialized as EMPTY
	    for (int z = 0; z < SIZE; z++) {
		System.out.print(gridOfSize.getCopyOfElement(i, z) + "|");
	    }
	}
	System.out.println();

	gridOfSize.setHorizontalLine(0, 0);
	gridOfSize.setHorizontalLine(1, 0);

	GridOption player = gridOfSize.getCurrentPlayerTurn();
	gridOfSize.setLine(1, 0);

	assertEquals(gridOfSize.getRemainingMoves(), (Integer) (gridOfSize.getTotalMoves() - 4));
	assertNotEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
	assertEquals(player, gridOfSize.getCurrentPlayerTurn()); // verifies if
								// the player
								// has received
								// a bonus move

	gridOfSize.setHorizontalLine(SIZE, 0);
	gridOfSize.setHorizontalLine(SIZE, SIZE - 1);
	gridOfSize.setLine(SIZE, 0);
	gridOfSize.setLine(SIZE, SIZE - 1);

	BaseGridImpl gridOfSize2 = new BaseGridImpl(SIZE);

	gridOfSize2.startMatch();

	for (int i = 0; i < SIZE + 1; i++) {
	    for (int z = 0; z < SIZE; z++) {
		gridOfSize2.setHorizontalLine(i, z);
		gridOfSize2.setLine(i, z);
	    }
	}

	assertTrue(gridOfSize2.getRemainingMoves().equals(0));
	System.out.println("Player1 " + gridOfSize2.getPlayerPoints(GridOption.PLAYER1) + " Player2 "
		+ gridOfSize2.getPlayerPoints(GridOption.PLAYER2));
	assertTrue(gridOfSize2.isEnded());
	assertNotEquals(GridOption.EMPTY, gridOfSize2.getWinner());
    }

    @Test
    public void testExceptions() {

	BaseGridImpl testGrid;

	try {
	    testGrid = new BaseGridImpl(SIZE - SIZE);
	} catch (IllegalArgumentException e) {
	} catch (Exception e) {
	    fail("Wrong exception thrown");
	}
	try {
	    testGrid = new BaseGridImpl(SIZE + SIZE);
	} catch (IllegalArgumentException e) {
	} catch (Exception e) {
	    fail("Wrong exception thrown");
	}

	testGrid = new BaseGridImpl(SIZE);
	try {
	    testGrid.setHorizontalLine(0, 0);
	} catch (IllegalStateException e) {
	} catch (Exception e) {
	    fail("Wrong exception thrown");
	}
	try {
	    testGrid.setHorizontalLine(-1, -1);
	} catch (IllegalArgumentException e) {
	} catch (Exception e) {
	    fail("Wrong exception thrown");
	}
	try {
	    testGrid.getWinner();
	} catch (IllegalStateException e){
	} catch (Exception e){
	    fail("Wrong exception thrown");
	}
    }
}
