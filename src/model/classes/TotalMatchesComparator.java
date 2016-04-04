package model.classes;

import java.util.Comparator;

/**
 * 
 * 
 *
 */
public class TotalMatchesComparator implements Comparator<PlayerImpl> {

    @Override
    public int compare(final PlayerImpl player1, final PlayerImpl player2) {

        if (player1.getTotalMatches().equals(player2.getTotalMatches())) {

            if (player1.getWinRate() == player2.getWinRate()) {

                if (player1.getTotalSquaresCatched().equals(player2.getTotalSquaresCatched())) {

                    return player1.getPlayerName().compareTo(player2.getPlayerName());
                }

                return (player1.getTotalSquaresCatched() > player2.getTotalSquaresCatched()) ? -1 : 1;

            }

            return (player1.getWinRate() > player2.getWinRate()) ? -1 : 1;
        }

        return (player1.getTotalMatches() > player2.getTotalMatches()) ? -1 : 1;

    }
}
