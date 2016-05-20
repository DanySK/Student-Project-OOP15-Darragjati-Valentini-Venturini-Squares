package it.unibo.squaresgameteam.squares.model.enumerations;

/**
 * This enum is used to order the ranking. The players can choose between four
 * ordering options.
 */
public enum RankingOption {

    /**
     * WINRATE is used to order the ranking by the winrate. TOTAL_WINS is used
     * to order the ranking by the number of wins. TOTAL_MATCHES is used to
     * order the ranking by the number of games played. TOTAL_POINTS_CATCHED is
     * used to order the ranking by the total points scored.
     */
    WINRATE, TOTAL_WINS, TOTAL_MATCHES, TOTAL_POINTS_SCORED;
}
