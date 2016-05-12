package model.enumerations;

/**
 * This enum represents the possibilities to order the ranking.
 */
public enum RankingOption {

    /**
     * WINRATE is used to order the ranking by the winrate.
     * TOTAL_WINS is used to order the ranking by the number of wins.
     * TOTAL_MATCHES is used to order the ranking by the number of games played.
     * TOTAL_SQUARES_CATCHED is used to order the ranking by the total points scored.
     */
    WINRATE, TOTAL_WINS, TOTAL_MATCHES, TOTAL_POINTS_SCORED;
}
