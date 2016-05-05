package model.classes;

import java.util.Comparator;

/**
 * 
 * 
 *
 */
public class TotalSquaresCatchedComparator implements Comparator<PlayerImpl> {
    
    @Override
    public int compare(final PlayerImpl player1, final PlayerImpl player2) {

        if (player1.getTotalPointsScored().equals(player2.getTotalPointsScored())) {

            if (player1.getWinRate() == player2.getWinRate()) {

                if (player1.getTotalMatches().equals(player2.getTotalMatches())) {

                    return player1.getPlayerName().compareTo(player2.getPlayerName());
                }

                return (player1.getTotalMatches() > player2.getTotalMatches()) ? -1 : 1;

            }

            return Double.compare(player1.getWinRate(), player2.getWinRate());

        }

        return (player1.getTotalPointsScored() > player2.getTotalPointsScored()) ? -1 : 1;
    }
}
