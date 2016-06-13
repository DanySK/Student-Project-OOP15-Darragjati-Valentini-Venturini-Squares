package it.unibo.squaresgameteam.squares.view.interfaces;

/**
 * This interface is used to choose the main options of the appication: to start
 * a new game, to show the rules, to show the ranking, to change the main
 * settings and to quit the application.
 */
public interface StartMenu {

  /**
   * This method starts a new match.
   */
  void startNewGame();

  /**
   * This method shows the rules of the game.
   */
  void showRules();

  /**
   * This method shows the ranking of the players.
   */
  void showRanking();

  /**
   * This method shows the options.
   */
  void showSettings();
}
