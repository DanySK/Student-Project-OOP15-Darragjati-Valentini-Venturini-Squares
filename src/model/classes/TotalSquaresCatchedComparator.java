package model.classes;

import java.util.Comparator;

public class TotalSquaresCatchedComparator implements Comparator<PlayerImpl> {

    @Override
    public int compare(final PlayerImpl player1, final PlayerImpl player2) {

        if (player1.getTotalSquaresCatched() == player2.getTotalSquaresCatched()) {

            if (player1.getWinRate() == player2.getWinRate()) {

                if (player1.getTotalMatches() == player2.getTotalMatches()) {
                    
                    return player1.getPlayerName().compareTo(player2.getPlayerName());
                } else {
                    
                    return (player1.getTotalMatches() > player2.getTotalMatches()) ? -1 : 1;
                }
            } else {
                
                return (player1.getWinRate() > player2.getWinRate()) ? -1 : 1;
            }
        } else{
            
            return (player1.getTotalSquaresCatched() > player2.getTotalSquaresCatched()) ? -1 : 1;
        }
    }
}
