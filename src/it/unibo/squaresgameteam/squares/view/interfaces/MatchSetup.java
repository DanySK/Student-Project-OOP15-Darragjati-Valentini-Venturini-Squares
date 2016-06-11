package it.unibo.squaresgameteam.squares.view.interfaces;

import it.unibo.squaresgameteam.squares.controller.enumerations.TypeGame;

/**
 * This interface is used to set all the needed fields before starting the new
 * match.
 */
public interface MatchSetup {

    /**
     * once all the fields are accomplished starts a new match.
     */
    void startMatch();

    /**
     * this method make the user choose between different playable fields.
     */
    TypeGame setBoardType();
}