package model.classes;

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
     * @param playerList
     *            the List of people that already palyed the game
     */
    // CHECKSTYLE:OFF:
    public RankingImpl(final List<PlayerImpl> playerList) {
        // CHECKSTYLE:ON:
        this.playerList = playerList;
        Integer occurrences = 0;

        for (PlayerImpl player : playerList) {
            occurrences = 0;
            for (PlayerImpl p : playerList) {
                if (player.getPlayerName().equals(p.getPlayerName())) {
                    occurrences++;
                }
            }
            if (occurrences > 1) {
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
    public List<PlayerImpl> orderListBy(final RankingOption option, final boolean reverseRanking) {

        switch (option) {
        case WINRATE:
            playerList.sort(new Comparator<PlayerImpl>() {

                @Override
                public int compare(final PlayerImpl player1, final PlayerImpl player2) {

                    if (player1.getWinRate() == player2.getWinRate()) {
                        if (player1.getTotalMatches().equals(player2.getTotalMatches())) {
                            if (player1.getTotalSquaresCatched().equals(player2.getTotalSquaresCatched())) {
                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }
                            return Integer.compare(player1.getTotalSquaresCatched(), player2.getTotalSquaresCatched());
                        }
                        return Integer.compare(player1.getTotalMatches(), player2.getTotalMatches());
                    }
                    return Double.compare(player1.getWinRate(), player2.getWinRate());
                }
            });
            break;
        case TOTAL_WINS:
            playerList.sort(new Comparator<PlayerImpl>() {

                @Override
                public int compare(final PlayerImpl player1, final PlayerImpl player2) {

                    if (player1.getTotalWins().equals(player2.getTotalWins())) {
                        if (player1.getWinRate() == player2.getWinRate()) {
                            if (player1.getTotalSquaresCatched().equals(player2.getTotalSquaresCatched())) {
                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }
                            return Integer.compare(player1.getTotalSquaresCatched(), player2.getTotalSquaresCatched());
                        }
                        return Double.compare(player1.getWinRate(), player2.getWinRate());
                    }
                    return Integer.compare(player1.getTotalWins(), player2.getTotalWins());

                }

            });
            break;
        case TOTAL_MATCHES:
            playerList.sort(new Comparator<PlayerImpl>() {

                @Override
                public int compare(final PlayerImpl player1, final PlayerImpl player2) {

                    if (player1.getTotalMatches().equals(player2.getTotalMatches())) {
                        if (player1.getWinRate() == player2.getWinRate()) {
                            if (player1.getTotalSquaresCatched().equals(player2.getTotalSquaresCatched())) {
                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }
                            return Integer.compare(player1.getTotalSquaresCatched(), player2.getTotalSquaresCatched());
                        }
                        return Double.compare(player1.getWinRate(), player2.getWinRate());
                    }
                    return Integer.compare(player1.getTotalMatches(), player2.getTotalMatches());
                }
            });
            break;
        case TOTAL_SQUARES_CATCHED:
            playerList.sort(new Comparator<PlayerImpl>() {

                @Override
                public int compare(final PlayerImpl player1, final PlayerImpl player2) {

                    if (player1.getTotalSquaresCatched().equals(player2.getTotalSquaresCatched())) {
                        if (player1.getWinRate() == player2.getWinRate()) {
                            if (player1.getTotalMatches().equals(player2.getTotalMatches())) {
                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }
                            return Integer.compare(player1.getTotalMatches(), player2.getTotalMatches());
                        }
                        return Double.compare(player1.getWinRate(), player2.getWinRate());
                    }
                    return Integer.compare(player1.getTotalSquaresCatched(), player2.getTotalSquaresCatched());
                }

            });
            break;
        default:
            throw new IllegalStateException();
        }
        if (reverseRanking) {
            Collections.reverse(playerList);
        }
        return Collections.unmodifiableList(playerList);
    }
}
