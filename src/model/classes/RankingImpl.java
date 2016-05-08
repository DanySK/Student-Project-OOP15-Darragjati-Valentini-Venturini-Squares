package model.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.enumerations.RankingOption;
import model.interfaces.Player;
import model.interfaces.Ranking;

/**
 * This class implements the interface Ranking. It is used to manage the ranking
 * system and to set the player's last match results.
 */
public class RankingImpl implements Ranking {

    private final List<Player> playerList;

    // CHECKSTYLE:OFF:
    public RankingImpl(final List<Player> playerList) {
        // CHECKSTYLE:ON:
        final List<String> playerNameList = new ArrayList<>();
        final Set<String> playerNameSet = new HashSet<>();
        for (final Player player : playerList) {
            playerNameList.add(player.getPlayerName());
            playerNameSet.add(player.getPlayerName());
        }
        if (playerNameList.size() > playerNameSet.size()) {
            throw new IllegalArgumentException();
        }
        this.playerList = playerList;
    }

    @Override
    public void addPlayerResults(final String playerName, final boolean victory, final Integer totalPointsScored) {
        for (final Player player : playerList) {
            if (player.getPlayerName().equals(playerName)) {
                addLastMatchResults(player, victory, totalPointsScored);
                return;
            }
        }
        final PlayerImpl newPlayer = new PlayerImpl();
        newPlayer.setPlayerName(playerName);
        playerList.add(newPlayer);
        addLastMatchResults(newPlayer, victory, totalPointsScored);
        
    }

    private void addLastMatchResults(final Player player, final boolean victory, final Integer totalPointsScored) {
        player.setTotalMatches(player.getTotalMatches() + 1);
        if (victory) {
            player.setWonMatches(player.getTotalWins() + 1);
        }    
        player.setTotalPointsScored(player.getTotalPointsScored() + totalPointsScored);
    }

    @Override
    public List<Player> orderListBy(final RankingOption option, final boolean reverseRanking) {

        switch (option) {
        case WINRATE:
            playerList.sort(new Comparator<Player>() {

                @Override
                public int compare(final Player player1, final Player player2) {

                    if (player1.getWinRate() == player2.getWinRate()) {
                        if (player1.getTotalMatches().equals(player2.getTotalMatches())) {
                            if (player1.getTotalPointsScored().equals(player2.getTotalPointsScored())) {
                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }
                            return Integer.compare(player1.getTotalPointsScored(), player2.getTotalPointsScored());
                        }
                        return Integer.compare(player1.getTotalMatches(), player2.getTotalMatches());
                    }
                    return Double.compare(player1.getWinRate(), player2.getWinRate());
                }
            });
            break;
        case TOTAL_WINS:
            playerList.sort(new Comparator<Player>() {

                @Override
                public int compare(final Player player1, final Player player2) {

                    if (player1.getTotalWins().equals(player2.getTotalWins())) {
                        if (player1.getWinRate() == player2.getWinRate()) {
                            if (player1.getTotalPointsScored().equals(player2.getTotalPointsScored())) {
                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }
                            return Integer.compare(player1.getTotalPointsScored(), player2.getTotalPointsScored());
                        }
                        return Double.compare(player1.getWinRate(), player2.getWinRate());
                    }
                    return Integer.compare(player1.getTotalWins(), player2.getTotalWins());

                }

            });
            break;
        case TOTAL_MATCHES:
            playerList.sort(new Comparator<Player>() {

                @Override
                public int compare(final Player player1, final Player player2) {

                    if (player1.getTotalMatches().equals(player2.getTotalMatches())) {
                        if (player1.getWinRate() == player2.getWinRate()) {
                            if (player1.getTotalPointsScored().equals(player2.getTotalPointsScored())) {
                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }
                            return Integer.compare(player1.getTotalPointsScored(), player2.getTotalPointsScored());
                        }
                        return Double.compare(player1.getWinRate(), player2.getWinRate());
                    }
                    return Integer.compare(player1.getTotalMatches(), player2.getTotalMatches());
                }
            });
            break;
        case TOTAL_SQUARES_CATCHED:
            playerList.sort(new Comparator<Player>() {

                @Override
                public int compare(final Player player1, final Player player2) {

                    if (player1.getTotalPointsScored().equals(player2.getTotalPointsScored())) {
                        if (player1.getWinRate() == player2.getWinRate()) {
                            if (player1.getTotalMatches().equals(player2.getTotalMatches())) {
                                return player1.getPlayerName().compareTo(player2.getPlayerName());
                            }
                            return Integer.compare(player1.getTotalMatches(), player2.getTotalMatches());
                        }
                        return Double.compare(player1.getWinRate(), player2.getWinRate());
                    }
                    return Integer.compare(player1.getTotalPointsScored(), player2.getTotalPointsScored());
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
