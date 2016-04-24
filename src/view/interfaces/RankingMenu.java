package view.interfaces;

/**
 * This interface is used to manage the ranking. The ordering options are: by
 * winrate, by total wins, by total matches and total conquered points.
 * 
 *
 */
public interface RankingMenu {

    /**
     * orders the ranking by win rate.
     */
    void orderByWinrate();

    /**
     * orders the ranking by total wins.
     */
    void orderByTotalWins();

    /**
     * orders the ranking by total matches.
     */
    void orderByTotalMatches();

    /**
     * orders the ranking by total conquered points.
     */
    void orderByTotalSquaresCatched();
}
