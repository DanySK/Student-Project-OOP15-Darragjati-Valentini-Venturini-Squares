package it.unibo.squaresgameteam.squares.controller.interfaces;

import java.io.IOException;

import it.unibo.squaresgameteam.squares.controller.enumerations.TypeGame;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;

/**
 * 
 * @author Licia Valentini Interface of the class MenuImpl
 */
public interface Menu {

    /**
     * This method reads the game's rules from txt file.
     * 
     * @return string with game's rules
     * @throws IOException .
     */

    String showRules() throws IOException;

    /**
     * this method create the object Match.
     * 
     * @param columsNumber
     *            columns number
     * @param rowsNumber
     *            rows numbers
     * @param namePlayer1
     *            player 1 name
     * @param namePlayer2
     *            player 2 name
     * @param mode
     *            game mode
     * @return match object
     */

    Match createMatch(int columsNumber, int rowsNumber, String namePlayer1, String namePlayer2, TypeGame mode);

    /**
     * this method create the object for the management of the ranking.
     * 
     * @return ranking object
     * @throws DuplicatedPlayerStatsException
     *             .
     */

    ShowRanking createRankingClass() throws DuplicatedPlayerStatsException;

    /**
     * this method ends the application.
     */

    void exitApp();

}
