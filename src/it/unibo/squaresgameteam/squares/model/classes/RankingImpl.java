package it.unibo.squaresgameteam.squares.model.classes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.interfaces.Player;
import it.unibo.squaresgameteam.squares.model.interfaces.Ranking;

/**
 * This class implements the interface Ranking. It is used to manage the ranking
 * system and to set the player's last match results.
 */
public class RankingImpl implements Ranking, Serializable {

    private static final long serialVersionUID = 5010913422706825614L;
    private final List<Player> playerList;

    /**
     * The consctructor is used to know the old players' matches results and
     * upate them if it is necessary.
     * 
     * @param playerList
     *            the old list of the players' ranking
     * @throws DuplicatedPlayerStatsException
     *             if the playerList contains two or more players with the same
     *             name
     */
    // CHECKSTYLE:OFF:
    public RankingImpl(final List<Player> playerList) throws DuplicatedPlayerStatsException {
        // CHECKSTYLE:ON:
        final List<String> playerNameList = new ArrayList<>();
        final Set<String> playerNameSet = new HashSet<>();
        for (final Player player : playerList) {
            playerNameList.add(player.getPlayerName());
            playerNameSet.add(player.getPlayerName());
        }
        if (playerNameList.size() > playerNameSet.size()) {
            throw new DuplicatedPlayerStatsException();
        }
        this.playerList = playerList;
    }

    @Override
    public void addPlayerResults(final Player player) {
        for (final Player p : playerList) {
            if (p.getPlayerName().equals(player.getPlayerName())) {
                final Player updatedPlayer = new PlayerImpl.Builder()
                                                           .playerName(player.getPlayerName())
                                                           .wonMatches(p.getWonMatches() + player.getWonMatches())
                                                           .totalMatches(p.getTotalMatches() + player.getTotalMatches())
                                                           .totalPointsScored(p.getTotalPointsScored() + player.getTotalPointsScored())
                                                           .build();
                playerList.remove(p);
                playerList.add(updatedPlayer);
                return;
            }
        }
        playerList.add(player);
    }

    @Override
    public void orderListBy(final RankingOption option, final boolean reverseRanking) {
        switch (option) {
        case WINRATE:
            playerList.sort(new Comparator<Player>() {

                @Override
                public int compare(final Player player1, final Player player2) {
                    if (compareWinrate(player1, player2).equals(0)) {
                        if (compareTotalMatches(player1, player2).equals(0)) {
                            if (compareTotalPointsScored(player1, player2).equals(0)) {
                                return compareNames(player1, player2);
                            }
                            return compareTotalPointsScored(player1, player2);
                        }
                        return compareTotalMatches(player1, player2);
                    }
                    return compareWinrate(player1, player2);
                }
            });
            break;
        case TOTAL_WINS:
            playerList.sort(new Comparator<Player>() {

                @Override
                public int compare(final Player player1, final Player player2) {
                    if (compareTotalWins(player1, player2).equals(0)) {
                        if (compareWinrate(player1, player2).equals(0)) {
                            if (compareTotalPointsScored(player1, player2).equals(0)) {
                                return compareNames(player1, player2);
                            }
                            return compareTotalPointsScored(player1, player2);
                        }
                        return compareWinrate(player1, player2);
                    }
                    return compareTotalWins(player1, player2);
                }
            });
            break;
        case TOTAL_MATCHES:
            playerList.sort(new Comparator<Player>() {

                @Override
                public int compare(final Player player1, final Player player2) {
                    if (compareTotalMatches(player1, player2).equals(0)) {
                        if (compareWinrate(player1, player2).equals(0)) {
                            if (compareTotalPointsScored(player1, player2).equals(0)) {
                                return compareNames(player1, player2);
                            }
                            return compareTotalPointsScored(player1, player2);
                        }
                        return compareWinrate(player1, player2);
                    }
                    return compareTotalMatches(player1, player2);
                }
            });
            break;
        case TOTAL_POINTS_SCORED:
            playerList.sort(new Comparator<Player>() {

                @Override
                public int compare(final Player player1, final Player player2) {
                    if (compareTotalPointsScored(player1, player2).equals(0)) {
                        if (compareWinrate(player1, player2).equals(0)) {
                            if (compareTotalMatches(player1, player2).equals(0)) {
                                return compareNames(player1, player2);
                            }
                            return compareTotalMatches(player1, player2);
                        }
                        return compareWinrate(player1, player2);
                    }
                    return compareTotalPointsScored(player1, player2);
                }
            });
            break;
        default:
            throw new IllegalArgumentException();
        }
        if (reverseRanking) {
            Collections.reverse(playerList);
        }
    }

    private Integer compareWinrate(final Player player1, final Player player2) {
        return Double.compare(player2.getWinRate(), player1.getWinRate());
    }

    private Integer compareTotalMatches(final Player player1, final Player player2) {
        return Integer.compare(player2.getTotalMatches(), player1.getTotalMatches());
    }

    private Integer compareTotalWins(final Player player1, final Player player2) {
        return Integer.compare(player2.getWonMatches(), player1.getWonMatches());
    }

    private Integer compareTotalPointsScored(final Player player1, final Player player2) {
        return Integer.compare(player2.getTotalPointsScored(), player1.getTotalPointsScored());
    }

    private Integer compareNames(final Player player1, final Player player2) {
        return player2.getPlayerName().compareTo(player1.getPlayerName());
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(playerList);
    }

    @Override
    public List<Player> getPlayerList() {
        return Collections.unmodifiableList(this.playerList);
    }
}
