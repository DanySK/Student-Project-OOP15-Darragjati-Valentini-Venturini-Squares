package it.unibo.squaresgameteam.squares.view.interfaces;

import it.unibo.squaresgameteam.squares.controller.enumerations.TypeGame;

/**
 * This interface is used to set all the needed fields and start a new match.
 */
public interface MatchSetup {

  /**
   * This method starts a new match.
   */
  void startMatch();

  /**
   * This method sets the game mode.
   */
  TypeGame setBoardType();
}