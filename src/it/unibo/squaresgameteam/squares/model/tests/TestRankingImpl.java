package it.unibo.squaresgameteam.squares.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import it.unibo.squaresgameteam.squares.model.classes.PlayerImpl;
import it.unibo.squaresgameteam.squares.model.classes.RankingImpl;
import it.unibo.squaresgameteam.squares.model.enumerations.RankingOption;
import it.unibo.squaresgameteam.squares.model.exceptions.DuplicatedPlayerStatsException;
import it.unibo.squaresgameteam.squares.model.interfaces.Player;

/**
 * Test the options offered by the class RankingImpl.
 */
public class TestRankingImpl {

    private static final String PLAYER1 = "Pippo";
    private static final String PLAYER2 = "Pluto";
    private static final String PLAYER3 = "Paperino";
    private static final String PLAYER4 = "Topolino";
    private static final String PLAYER5 = "Paperone";
    // CHECKSTYLE:OFF:
    private List<Player> createPlayers() {
        final PlayerImpl player1 = new PlayerImpl.Builder()
                                                 .playerName(PLAYER1)
                                                 .wonMatches(1)
                                                 .totalMatches(10)
                                                 .totalPointsScored(51)
                                                 .build();
        assertEquals(player1.getWinRate(), 10.0, 0);

        final PlayerImpl player2 = new PlayerImpl.Builder()
                                                 .playerName(PLAYER2)
                                                 .wonMatches(8)
                                                 .totalMatches(10)
                                                 .totalPointsScored(456)
                                                 .build();
        assertTrue(player2.getWinRate() > player1.getWinRate());

        final PlayerImpl player3 = new PlayerImpl.Builder()
                                                 .playerName(PLAYER3)
                                                 .wonMatches(4)
                                                 .totalMatches(5)
                                                 .totalPointsScored(223)
                                                 .build();
        assertEquals(player3.getWinRate(), player2.getWinRate(), 0);
        final List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        return playerList;
    }

    /**
     * Base option test.
     * 
     * @throws DuplicatedPlayerStatsException
     */
    @Test
    public void test() throws DuplicatedPlayerStatsException {
        List<Player> orderingTest;
        final List<Player> playerList = createPlayers();
        assertEquals(playerList.size(), 3);
        
        final RankingImpl rankingTest = new RankingImpl(playerList);
        final Player newPlayer = new PlayerImpl.Builder()
                                               .playerName(PLAYER4)
                                               .wonMatches(1)
                                               .totalMatches(1)
                                               .totalPointsScored(37)
                                               .build();
        rankingTest.addPlayerResults(newPlayer);
        assertEquals(playerList.size(), 4);
        // ordering list by WINRATE
        rankingTest.orderListBy(RankingOption.WINRATE, false);
        orderingTest = rankingTest.getPlayerList();
        assertEquals(orderingTest.get(0).getPlayerName(), PLAYER4);
        assertEquals(orderingTest.get(1).getPlayerName(), PLAYER2);
        assertEquals(orderingTest.get(2).getPlayerName(), PLAYER3);
        assertEquals(orderingTest.get(3).getPlayerName(), PLAYER1);
        // ordering list by TOTAL WINS
        rankingTest.orderListBy(RankingOption.TOTAL_WINS, false);
        orderingTest = rankingTest.getPlayerList();
        assertEquals(orderingTest.get(0).getPlayerName(), PLAYER2);
        assertEquals(orderingTest.get(1).getPlayerName(), PLAYER3);
        assertEquals(orderingTest.get(2).getPlayerName(), PLAYER4);
        assertEquals(orderingTest.get(3).getPlayerName(), PLAYER1);
        // ordering list by TOTAL MATCHES done
        rankingTest.orderListBy(RankingOption.TOTAL_MATCHES, false);
        orderingTest = rankingTest.getPlayerList();
        assertEquals(orderingTest.get(0).getPlayerName(), PLAYER2);
        assertEquals(orderingTest.get(1).getPlayerName(), PLAYER1);
        assertEquals(orderingTest.get(2).getPlayerName(), PLAYER3);
        assertEquals(orderingTest.get(3).getPlayerName(), PLAYER4);
        // ordering list by TOTSL SQUARES CATCHED
        rankingTest.orderListBy(RankingOption.TOTAL_POINTS_SCORED, false);
        orderingTest = rankingTest.getPlayerList();
        assertEquals(orderingTest.get(0).getPlayerName(), PLAYER2);
        assertEquals(orderingTest.get(1).getPlayerName(), PLAYER3);
        assertEquals(orderingTest.get(2).getPlayerName(), PLAYER1);
        assertEquals(orderingTest.get(3).getPlayerName(), PLAYER4);

        final Player newPlayer2 = new PlayerImpl.Builder()
                                                .playerName(PLAYER5)
                                                .wonMatches(1)
                                                .totalMatches(1)
                                                .totalPointsScored(37)
                                                .build();
        rankingTest.addPlayerResults(newPlayer2);
        // ordering list by WINRATE
        rankingTest.orderListBy(RankingOption.WINRATE, false);
        final List<Player> testWinRateOrderedList = rankingTest.getPlayerList();
        assertEquals(testWinRateOrderedList.get(0).getPlayerName(), PLAYER4);
        assertEquals(testWinRateOrderedList.get(1).getPlayerName(), PLAYER5);
        assertEquals(testWinRateOrderedList.get(2).getPlayerName(), PLAYER2);
        assertEquals(testWinRateOrderedList.get(3).getPlayerName(), PLAYER3);
        assertEquals(testWinRateOrderedList.get(4).getPlayerName(), PLAYER1);
        // reversing the list ordered by WINRATE
        rankingTest.orderListBy(RankingOption.WINRATE, true);
        final List<Player> testReverseOrderedList = rankingTest.getPlayerList();
        assertEquals(testReverseOrderedList.get(0).getPlayerName(), PLAYER1);
        assertEquals(testReverseOrderedList.get(1).getPlayerName(), PLAYER3);
        assertEquals(testReverseOrderedList.get(2).getPlayerName(), PLAYER2);
        assertEquals(testReverseOrderedList.get(3).getPlayerName(), PLAYER5);
        assertEquals(testReverseOrderedList.get(4).getPlayerName(), PLAYER4);
    }

    /**
     * Exceptions test.
     * 
     * @throws DuplicatedPlayerStatsException
     */
    @Test
    // CHECKSTYLE:OFF:
    public void testExceptions() throws DuplicatedPlayerStatsException {
        final List<Player> playerList = createPlayers();
        RankingImpl testListException = new RankingImpl(playerList);
        Player testPlayer;
        try {
            testPlayer = new PlayerImpl.Builder().playerName(PLAYER4).wonMatches(5).totalMatches(6)
                    .totalPointsScored(467).build();
            testListException.getPlayerList().add(testPlayer);
            fail("You can't add a player to the list, it should be unmodifiable");
        } catch (UnsupportedOperationException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        try {
            testPlayer = new PlayerImpl.Builder().playerName(PLAYER1).wonMatches(5).totalMatches(6)
                    .totalPointsScored(467).build();
            playerList.add(testPlayer);
            testListException = new RankingImpl(playerList);
            fail("The list can't contain two players with the same name");
        } catch (DuplicatedPlayerStatsException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        try {
            testPlayer = new PlayerImpl.Builder().playerName(PLAYER4).wonMatches(6).totalMatches(5)
                    .totalPointsScored(467).build();
            fail("The won matches cant be more than the total matches");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
    }
    
    
}
