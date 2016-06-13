package it.unibo.squaresgameteam.squares.view.interfaces;

/**
 * This interface is used to manage the ranking. The ordering options are: by
 * winrate, by total wins, by total matches and total conquered points.
 */
public interface RankingMenu {

  /**
   * Orders the ranking by win rate.
   */
  void orderByWinrate();

  /**
   * Orders the ranking by total wins.
   */
  void orderByTotalWins();

  /**
   * Orders the ranking by total matches.
   */
  void orderByTotalMatches();

  /**
   * Orders the ranking by total conquered points.
   */
  void orderByTotalPointsScored();
}
