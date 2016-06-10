package it.unibo.squaresgameteam.squares.view.interfaces;

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
     * if it is checked the playing field will be borderless.
     */
    void borderless();

    /**
     * this method make the user choose between different playable fields.
     */
    void setBoardType();
}