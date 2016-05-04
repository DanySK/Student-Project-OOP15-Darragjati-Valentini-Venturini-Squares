package model.classes;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.enumerations.RankingOption;
import model.interfaces.Ranking;

/**
 * This class implements the interface Ranking. It is used to manage the ranking
 * system and to set the player's last match results.
 */
public class RankingImpl implements Ranking {

    private final List<PlayerImpl> playerList;

    // CHECKSTYLE:OFF:
    public RankingImpl(final List<PlayerImpl> playerList) {
        // CHECKSTYLE:ON:
        final Set<PlayerImpl> playerSet = new HashSet<>(playerList);
        if (playerList.size() > playerSet.size()) {
            throw new IllegalArgumentException();
        }
        this.playerList = playerList;
    }

    @Override
    public void addPlayerResults(final String playerName, final boolean victory, final Integer totalSquaresCatched) {
        for (final PlayerImpl player : playerList) {
            if (player.getPlayerName().equals(playerName)) {
                player.addLastMatchResults(victory, totalSquaresCatched);
                return;
            }
        }
        final PlayerImpl newPlayer = new PlayerImpl(playerName);
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
