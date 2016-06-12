package it.unibo.squaresgameteam.squares.controller.interfaces;

import java.io.IOException;

import it.unibo.squaresgameteam.squares.controller.enumerations.TypeGame;
import it.unibo.squaresgameteam.squares.model.enumerations.ListType;
import it.unibo.squaresgameteam.squares.model.exceptions.UnsupportedSizeException;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.exceptions.NoMovesDoneException;
import it.unibo.squaresgameteam.squares.model.exceptions.UnexistentLineListException;
import it.unibo.squaresgameteam.squares.model.interfaces.Move;

/**
 * Interface of class MatchImpl.
 * 
 * @author Licia Valentini
 *
 */
public interface Match {

    /**
     * This method creates a new grid.
     * 
     * @throws UnsupportedSizeException .
     */
    void createGrid() throws UnsupportedSizeException;

    /**
     * This method creates a new match and starts the match time.
     * 
     */
    void createNewMatch();

    /**
     * This method allows to enter a move.
     * If the match is ended sets the winner's name and adds the result to the ranking. 
     * 
     * @param direction
     *            Vertical, horizontal or diagonal.
     * @param numLine
     *            line number.
     * @param position
     *            position.
     * 
     * @throws UnexistentLineListException .
     * @throws DuplicatedPlayerStatsException .
     * @throws IOException .
     * @throws ClassNotFoundException .
     */
    void addLine(ListType direction, int numLine, int position)
            throws UnexistentLineListException, IOException, DuplicatedPlayerStatsException, ClassNotFoundException;

    /**
     * This method makes the undo of the last move.
     * 
     * @throws NoMovesDoneException
     *             .
     * @throws UnexistentLineListException
     *             .
     */
    void undo() throws NoMovesDoneException, UnexistentLineListException;

    /**
     * 
     * @return the score of the current player
     */
    int getCurrentPlayerScore();

    /**
     * 
     * @return the total time at the end of the match.
     */
    Double getMatchTime();

    /**
     * 
     * @return true if the match is ended
     */

    boolean isEnded();

    /**
     * 
     * @return player 1 name
     */

    String getNamePlayer1();

    /**
     * 
     * @return player 2 name
     */

    String getNamePlayer2();

    /**
     * 
     * @return columns number
     */

    int getColumsNumber();

    /**
     * 
     * @return rows number
     */
    int getRowsNumber();

    /**
     * 
     * @return game mode
     */

    TypeGame getMode();

    /**
     * 
     * @return copy of last move
     */

    Move getLastMove();
    
    /**
     * 
     * @return the player who has to do the next move
     */

    String getCurrentPlayerTurn();
    
    /**
     * 
     * @return true if the match's result is par.
     */

    boolean isPar();

}
