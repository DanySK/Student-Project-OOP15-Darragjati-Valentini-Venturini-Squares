package it.unibo.squaresgameteam.squares.view.interfaces;

/**
 * This interface is used to customize the application's interface. The user can
 * lower or increase the application's volume and choose the colors of the
 * background and the players.
 */
public interface OptionsMenu {

  /**
   * sets on/off the application's music.
   */
  void setMusic();

  /**
   * resets the application's ranking.
   */
  void resetRanking();

  /**
   * sets the first player's color.
   */
  void setFirstPlayerColor();

  /**
   * sets the second player's color.
   */
  void setSecondPlayerColor();

  /**
   * sets the application's background.
   */
  void setBackground();
}
