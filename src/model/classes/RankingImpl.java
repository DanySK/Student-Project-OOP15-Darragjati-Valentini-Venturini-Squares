package model.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.enumerations.RankingOption;
import model.interfaces.Ranking;

/**
 * 
 * 
 *
 */
public class RankingImpl implements Ranking {

    private List<PlayerImpl> playerList;

    /**
     * 
     * @param playerList the List of people that already palyed the game
     */
    public RankingImpl(final List<PlayerImpl> playerList) {
        this.playerList = playerList;
        Integer listIndex = 0;
        Integer occurrences = 0;
   
        for (PlayerImpl player : playerList) {
            occurrences = 0;
            while (playerList.iterator().hasNext()) {
                if (player.getPlayerName().equals(playerList.get(listIndex).getPlayerName())) {
                    occurrences++;
                }
            }
            if (occurrences == 1) {
                System.out.println("errore");
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void addPlayerResults(final String playerName, final boolean victory, final Integer totalSquaresCatched) {

        for (PlayerImpl p : playerList) {
            if (p.getPlayerName().equals(playerName)) {
                p.addLastMatchResults(victory, totalSquaresCatched);
                return;
            }
        }

        PlayerImpl newPlayer = new PlayerImpl(playerName);
        newPlayer.addLastMatchResults(victory, totalSquaresCatched);
        playerList.add(newPlayer);
    }

    @Override
    public List<PlayerImpl> orderListBy(final RankingOption option) {

        switch (option) {
        case WINRATE:
            playerList.sort(new Comparator<PlayerImpl>() {

                @Override
                public int compare(PlayerImpl player1, PlayerImpl player2) {
                    
                    if (Double.compare(player1.getWinRate(), player2.getWinRate()) == 0) {

                        if (player1.getTotalMatches().equals(player2.getTotalMatches())) {

                            if (player1.getTotalSquaresCatched().equals(player2.getTotalSquaresCatched())) {

                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }

                            return (player1.getTotalSquaresCatched() > player2.getTotalSquaresCatched()) ? -1 : 1;
                        }

                        return (player1.getTotalMatches() > player2.getTotalMatches()) ? -1 : 1;
                    }

                    return Double.compare(player1.getWinRate(), player2.getWinRate());
                }
            });
            break;
        case TOTAL_WINS:
            playerList.sort(new Comparator<PlayerImpl>() {

                @Override
                public int compare(PlayerImpl player1, PlayerImpl player2) {
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
                
            });
            break;
        case TOTAL_MATCHES:
            playerList.sort(new Comparator<PlayerImpl>() {

                @Override
                public int compare(PlayerImpl player1, PlayerImpl player2) {
                    if (player1.getTotalMatches().equals(player2.getTotalMatches())) {

                        if (player1.getWinRate() == player2.getWinRate()) {

                            if (player1.getTotalSquaresCatched().equals(player2.getTotalSquaresCatched())) {

                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }

                            return (player1.getTotalSquaresCatched() > player2.getTotalSquaresCatched()) ? -1 : 1;

                        }

                        return Double.compare(player1.getWinRate(), player2.getWinRate()); // da
                                                                                          // verificare
                                                                                          // dovrebbe
                                                                                          // fare
                                                                                          // l'azione
                                                                                          // inversa
                                                                                          // rispetto
                                                                                          // a
                                                                                          // quella
                                                                                          // richiesta
                    }

                    return (player1.getTotalMatches() > player2.getTotalMatches()) ? -1 : 1;

                }
                
            });
            break;
        case TOTAL_SQUARES_CATCHED:
            playerList.sort(new Comparator<PlayerImpl>() {

                @Override
                public int compare(PlayerImpl player1, PlayerImpl player2) {
                    if (player1.getTotalSquaresCatched().equals(player2.getTotalSquaresCatched())) {

                        if (player1.getWinRate() == player2.getWinRate()) {

                            if (player1.getTotalMatches().equals(player2.getTotalMatches())) {

                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }

                            return (player1.getTotalMatches() > player2.getTotalMatches()) ? -1 : 1;

                        }

                        return Double.compare(player1.getWinRate(), player2.getWinRate());

                    }

                    return (player1.getTotalSquaresCatched() > player2.getTotalSquaresCatched()) ? -1 : 1;
                }
                
            });
            break;
        default:
            throw new IllegalStateException();
        }

        return Collections.unmodifiableList(playerList);
    }

    @Override
    public List<PlayerImpl> reverseRanking(final RankingOption option) {

        List<PlayerImpl> reverseList = new ArrayList<>();

        for (int i = orderListBy(option).size() - 1; i > 0; i--) {
            reverseList.add(orderListBy(option).get(i));
        }
        return Collections.unmodifiableList(reverseList);
    }

}
