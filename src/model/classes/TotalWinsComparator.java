package model.classes;

import java.util.Comparator;

/**
 * 
 * 
 *
 */
public class TotalWinsComparator implements Comparator<PlayerImpl> {

    @Override
    public int compare(final PlayerImpl player1, final PlayerImpl player2) {

        if (player1.getTotalWins().equals(player2.getTotalWins())) {

            if (player1.getWinRate() == player2.getWinRate()) {

                    if (player1.getTotalSquaresCatched().equals(player2.getTotalSquaresCatched())) {

                        return player1.getPlayerName().compareTo(player2.getPlayerName());
                    }

                    return player1.getTotalSquaresCatched() > player2.getTotalSquaresCatched() ? -1 : 1;
                }
            
            return Double.compare(player1.getWinRate(), player2.getWinRate());
        }
        
        return player1.getTotalWins() > player2.getTotalWins() ? -1 : 1;
    }

}
