package it.unibo.squaresgameteam.squares.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import it.unibo.squaresgameteam.squares.model.classes.GameImpl;
import it.unibo.squaresgameteam.squares.model.classes.MoveImpl;
import it.unibo.squaresgameteam.squares.model.classes.SquareGridImpl;
import it.unibo.squaresgameteam.squares.model.enumerations.GridOption;
import it.unibo.squaresgameteam.squares.model.enumerations.ListType;
import it.unibo.squaresgameteam.squares.model.exceptions.UnsupportedSizeException;
import it.unibo.squaresgameteam.squares.model.exceptions.MoveAlreadyDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.NoMovesDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.BaseGrid;
import it.unibo.squaresgameteam.squares.model.interfaces.Game;
import it.unibo.squaresgameteam.squares.model.interfaces.Move;

/**
 * This class simulates the possible moves of a game.
 */
public class TestSquareGame {

    private static final Integer STANDARD_SIZE = 6;
    private static final Integer HORIZONTAL_SIZE = 5;
    private static final Integer VERTICAL_SIZE = 4;
    private static final String ERROR = "Wrong exception thrown";
    private Move move;

    /**
     * Tests the basic move option offered by a square game.
     * 
     * @throws UnsupportedSizeException
     *             if the list chosen is not supported by the game mode
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    @Test
    public void testBasicMoveOperation() throws UnsupportedSizeException, UnexistentLineListException {

        final BaseGrid squareGrid = new SquareGridImpl(HORIZONTAL_SIZE, VERTICAL_SIZE);
        final Game gridOfSize = new GameImpl(squareGrid, "Rei Ayanami", "Shinji Ikari");

        // verifies that total moves are the same as remaining moves
        assertEquals(squareGrid.getTotalMoves(), squareGrid.getRemainingMoves());

        // verifies that every element in the lists is initialized as EMPTY
        for (int i = 0; i < HORIZONTAL_SIZE + 1; i++) {
            for (int p = 0; p < VERTICAL_SIZE; p++) {        
                assertEquals(squareGrid.getWhoSetTheLine(new MoveImpl(ListType.HORIZONTAL, i, p)),
                        GridOption.EMPTY);
            }
        }
        for (int i = 0; i < VERTICAL_SIZE + 1; i++) {
            for (int p = 0; p < HORIZONTAL_SIZE; p++) {    
                assertEquals(squareGrid.getWhoSetTheLine(new MoveImpl(ListType.VERTICAL, i, p)), GridOption.EMPTY);
            }
        }
        assertSame(VERTICAL_SIZE * HORIZONTAL_SIZE, squareGrid.getMatchMaximumPoints());
        assertFalse(gridOfSize.isStarted());
        gridOfSize.startMatch();
        assertTrue(gridOfSize.isStarted());
        move = new MoveImpl(ListType.VERTICAL, 0, 0);
        gridOfSize.setLine(move);
        assertEquals(squareGrid.getRemainingMoves(), (Integer) (squareGrid.getTotalMoves() - 1));
        assertEquals(gridOfSize.getCopyOfLastMove().getListType(), ListType.VERTICAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getListIndex(), (Integer) 0);
        assertEquals(gridOfSize.getCopyOfLastMove().getPosition(), (Integer) 0);
        move = new MoveImpl(ListType.HORIZONTAL, 0, 0);
        gridOfSize.setLine(move);
        move = new MoveImpl(ListType.HORIZONTAL, 1, 0);
        gridOfSize.setLine(move);
        // this player turn memorization is used later to check if the turn
        // switch is correctly implemented
        final GridOption player = gridOfSize.getCurrentPlayerTurn();
        move = new MoveImpl(ListType.VERTICAL, 1, 0);
        gridOfSize.setLine(move);
        assertEquals(squareGrid.getRemainingMoves(), (Integer) (squareGrid.getTotalMoves() - 4));
        assertNotEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        // verifies if the player has received a bonus move
        assertEquals(player, gridOfSize.getCurrentPlayerTurn());

        // test if the undo is working correctly
        gridOfSize.undoLastMove();
        assertEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        assertEquals(player, gridOfSize.getCurrentPlayerTurn());
        assertEquals(gridOfSize.getCopyOfLastMove().getListType(), ListType.HORIZONTAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getListIndex(), (Integer) 1);
        assertEquals(gridOfSize.getCopyOfLastMove().getPosition(), (Integer) 0);
        // verifies that the player points are now the same
        assertEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
        gridOfSize.undoLastMove();
        assertNotEquals(player, gridOfSize.getCurrentPlayerTurn());

        // verifies that you can't modify the last move
        gridOfSize.getCopyOfLastMove().setListType(ListType.VERTICAL);
        gridOfSize.getCopyOfLastMove().setListIndex(3);
        gridOfSize.getCopyOfLastMove().setPosition(3);
        assertEquals(gridOfSize.getCopyOfLastMove().getListType(), ListType.HORIZONTAL);
        assertEquals(gridOfSize.getCopyOfLastMove().getListIndex(), (Integer) 0);
        assertEquals(gridOfSize.getCopyOfLastMove().getPosition(), (Integer) 0);

        // check if the other method of points assignaments works correctly
        move = new MoveImpl(ListType.HORIZONTAL, 1, 1);
        gridOfSize.setLine(move);
        move = new MoveImpl(ListType.VERTICAL, 1, 1);
        gridOfSize.setLine(move);
        move = new MoveImpl(ListType.VERTICAL, 2, 1);
        gridOfSize.setLine(move);
        move = new MoveImpl(ListType.HORIZONTAL, 2, 1);
        gridOfSize.setLine(move);
        assertNotEquals(gridOfSize.getPlayerPoints(GridOption.PLAYER1), gridOfSize.getPlayerPoints(GridOption.PLAYER2));
    }

    /**
     * Tests a complete match.
     * 
     * @throws UnsupportedSizeException
     *             if the list chosen is not supported by the game mode
     * @throws UnexistentLineListException
     *             if the listIndex input is not correctF
     */
    @Test
    public void testCompleteMatch() throws UnsupportedSizeException, UnexistentLineListException {
        final BaseGrid squareGrid2 = new SquareGridImpl(STANDARD_SIZE, STANDARD_SIZE);
        final Game gridOfSize2 = new GameImpl(squareGrid2, "Rei Ayanami", "Shinji Ikari");
        gridOfSize2.startMatch();
        // fills the grid with all the possible moves
        for (int i = 0; i < STANDARD_SIZE + 1; i++) {
            for (int p = 0; p < STANDARD_SIZE; p++) {
                gridOfSize2.setLine(new MoveImpl(ListType.HORIZONTAL, i, p));
                gridOfSize2.setLine(new MoveImpl(ListType.VERTICAL, i, p));                
            }
        }
        assertTrue(squareGrid2.getRemainingMoves().equals(0));
        assertNotEquals(gridOfSize2.getPlayerPoints(GridOption.PLAYER1),
                gridOfSize2.getPlayerPoints(GridOption.PLAYER2));
        assertEquals(gridOfSize2.getPlayerPoints(GridOption.PLAYER1) + gridOfSize2.getPlayerPoints(GridOption.PLAYER2), 36);
        assertTrue(gridOfSize2.isEnded());
        assertNotEquals(GridOption.EMPTY, gridOfSize2.getWinner());
        assertNotEquals(gridOfSize2.getPlayerMatchResult(GridOption.PLAYER1),
                gridOfSize2.getPlayerMatchResult(GridOption.PLAYER2));
        System.out.println(gridOfSize2.getMatchDuration());
        assertNotEquals(gridOfSize2.getMatchDuration(), -1.0);
    }

    /**
     * Test if the exceptions are thrown correctly.
     * 
     * @throws UnsupportedSizeException
     * @throws UnexistentLineListException
     */
    @Test
    public void testExceptions() throws UnsupportedSizeException, UnexistentLineListException {
        BaseGrid exceptionGrid;
        Game exceptionGame;

        try {
            exceptionGrid = new SquareGridImpl(STANDARD_SIZE - 4, STANDARD_SIZE - 4);
            fail("Can't create a grid too small");
        } catch (UnsupportedSizeException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGrid = new SquareGridImpl(STANDARD_SIZE + STANDARD_SIZE, STANDARD_SIZE + STANDARD_SIZE);
            fail("Can't create a grid too big");
        } catch (UnsupportedSizeException e) {
        } catch (Exception e) {
            fail(ERROR);
        }

        exceptionGrid = new SquareGridImpl(STANDARD_SIZE, STANDARD_SIZE);
        exceptionGame = new GameImpl(exceptionGrid, "Rei Ayanami", "Shinji Ikari");
        try {
            exceptionGame.isEnded();
            fail("the match can't be ended if it isn't started");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGame.getCurrentPlayerTurn();
            fail("you can't get the player turn if the match isn't started");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            move = new MoveImpl(ListType.HORIZONTAL, 0, 0);
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
            move.setPosition(5);
            exceptionGame.setLine(move);
            fail("Can't insert those parameters");
        } catch (UnexistentLineListException e) {
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
        } catch (NoMovesDoneException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGame.getCopyOfLastMove();
            fail("You can't get the last move if noone of the two players have set one");
        } catch (NoMovesDoneException e) {
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
            move.setListType(ListType.HORIZONTAL);
            move.setListIndex(0);
            move.setPosition(0);
            exceptionGame.setLine(move);
            exceptionGame.setLine(move);
            fail("You can't set a move where another player has already set it");
        } catch (MoveAlreadyDoneException e) {
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
        try {
            exceptionGame.getPlayerMatchResult(GridOption.PLAYER1);
            fail("You can't get the player match results if the match is not ended");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
        try {
            exceptionGame.getMatchDuration();
            fail("You can't get the match duration if the match is not ended");
        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail(ERROR);
        }

        SquareGridImpl exceptionGrid2 = new SquareGridImpl(STANDARD_SIZE, STANDARD_SIZE);
        Game exceptionGame2 = new GameImpl(exceptionGrid2, "Rei Ayanami", "Shinji Ikari");
        exceptionGame2.startMatch();
        for (int i = 0; i < STANDARD_SIZE + 1; i++) {
            for (int z = 0; z < STANDARD_SIZE; z++) {
                move.setListType(ListType.HORIZONTAL);
                move.setListIndex(i);
                move.setPosition(z);
                exceptionGame2.setLine(move);
                move.setListType(ListType.VERTICAL);
                exceptionGame2.setLine(move);
            }
        }
        try {
            exceptionGame2.getPlayerMatchResult(GridOption.EMPTY);
            fail("You can't get put as parameter GridOption.EMPTY, it is not a player!");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail(ERROR);
        }
    }
}
