package model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import model.classes.PlayerImpl;
import model.classes.RankingImpl;
import model.enumerations.RankingOption;
import model.exceptions.DuplicatedPlayerStatsException;
import model.interfaces.Player;

/**
 * Test the options offered by the class RankingImpl.
 * 
 *
 */
public class TestRankingImpl {

    private static final String PLAYER1 = "Pippo";
    private static final String PLAYER2 = "Pluto";
    private static final String PLAYER3 = "Paperino";
    private static final String PLAYER4 = "Topolino";
    private static final String PLAYER5 = "Paperone";

    private List<Player> createPlayers() {
        // CHECKSTYLE:OFF:
        final PlayerImpl player1 = new PlayerImpl(PLAYER1, 1, 10, 51);
        assertEquals(player1.getWinRate(), 10.0, 0);

        final PlayerImpl player2 = new PlayerImpl(PLAYER2, 8, 10, 456);
        assertTrue(player2.getWinRate() > player1.getWinRate());

        final PlayerImpl player3 = new PlayerImpl(PLAYER3, 4, 5, 223);
        assertEquals(player3.getWinRate(), player2.getWinRate(), 0);
        // CHECKSTYLE:ON:
        final List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        return playerList;
    }

    /**
     * Base option test.
     * @throws DuplicatedPlayerStatsException 
     */
    @Test
    public void test() throws DuplicatedPlayerStatsException {
        final List<Player> playerList = createPlayers();
        assertEquals(playerList.size(), 3);

        final RankingImpl rankingTest = new RankingImpl(playerList);
        // CHECKSTYLE:OFF:
        rankingTest.addPlayerResults(PLAYER4, true, 37);
        // CHECKSTYLE:ON:
        assertEquals(playerList.size(), 4);
        // ordering list by WINRATE
        List<Player> orderingTest = rankingTest.orderListBy(RankingOption.WINRATE, false);
        assertEquals(orderingTest.get(0).getPlayerName(), PLAYER1);
        assertEquals(orderingTest.get(1).getPlayerName(), PLAYER3);
        assertEquals(orderingTest.get(2).getPlayerName(), PLAYER2);
        assertEquals(orderingTest.get(3).getPlayerName(), PLAYER4);
        // ordering list by TOTAL WINS
        orderingTest = rankingTest.orderListBy(RankingOption.TOTAL_WINS, false);
        assertEquals(orderingTest.get(0).getPlayerName(), PLAYER1);
        assertEquals(orderingTest.get(1).getPlayerName(), PLAYER4);
        assertEquals(orderingTest.get(2).getPlayerName(), PLAYER3);
        assertEquals(orderingTest.get(3).getPlayerName(), PLAYER2);
        // ordering list by TOTAL MATCHES done
        orderingTest = rankingTest.orderListBy(RankingOption.TOTAL_MATCHES, false);
        assertEquals(orderingTest.get(0).getPlayerName(), PLAYER4);
        assertEquals(orderingTest.get(1).getPlayerName(), PLAYER3);
        assertEquals(orderingTest.get(2).getPlayerName(), PLAYER1);
        assertEquals(orderingTest.get(3).getPlayerName(), PLAYER2);
        // ordering list by TOTSL SQUARES CATCHED
        orderingTest = rankingTest.orderListBy(RankingOption.TOTAL_POINTS_SCORED, false);
        assertEquals(orderingTest.get(0).getPlayerName(), PLAYER4);
        assertEquals(orderingTest.get(1).getPlayerName(), PLAYER1);
        assertEquals(orderingTest.get(2).getPlayerName(), PLAYER3);
        assertEquals(orderingTest.get(3).getPlayerName(), PLAYER2);

        // CHECKSTYLE:OFF:
        rankingTest.addPlayerResults(PLAYER5, true, 37);
        // CHECKSTYLE:ON:
        // ordering list by WINRATE
        final List<Player> testWinRateOrderedList = rankingTest.orderListBy(RankingOption.WINRATE, false);
        assertEquals(testWinRateOrderedList.get(0).getPlayerName(), PLAYER1);
        assertEquals(testWinRateOrderedList.get(1).getPlayerName(), PLAYER3);
        assertEquals(testWinRateOrderedList.get(2).getPlayerName(), PLAYER2);
        assertEquals(testWinRateOrderedList.get(3).getPlayerName(), PLAYER5);
        assertEquals(testWinRateOrderedList.get(4).getPlayerName(), PLAYER4);
        // reversing the list ordered by WINRATE
        final List<Player> testReverseOrderedList = rankingTest.orderListBy(RankingOption.WINRATE, true);
        assertEquals(testReverseOrderedList.get(0).getPlayerName(), PLAYER4);
        assertEquals(testReverseOrderedList.get(1).getPlayerName(), PLAYER5);
        assertEquals(testReverseOrderedList.get(2).getPlayerName(), PLAYER2);
        assertEquals(testReverseOrderedList.get(3).getPlayerName(), PLAYER3);
        assertEquals(testReverseOrderedList.get(4).getPlayerName(), PLAYER1);
    }

    /**
     * Exceptions Test.
     * @throws DuplicatedPlayerStatsException 
     */
    @Test
    //CHECKSTYLE:OFF:
    public void testExceptions() throws DuplicatedPlayerStatsException {
        final List<Player> playerList = createPlayers();
        final Player testPlayer = new PlayerImpl(PLAYER1, 0, 0,0);
        RankingImpl testListException = new RankingImpl(playerList);
        try {
            testPlayer.setPlayerName(PLAYER4);
            testPlayer.setTotalMatches(6);
            testPlayer.setWonMatches(5);
            testPlayer.setTotalPointsScored(467);
            testListException.orderListBy(RankingOption.WINRATE, false).add(testPlayer);
            fail("You can't add a player to the list, it should be unmodifiable");
        } catch (UnsupportedOperationException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        try {
            testPlayer.setPlayerName(PLAYER1);
            testPlayer.setTotalMatches(6);
            testPlayer.setWonMatches(5);
            testPlayer.setTotalPointsScored(467);
            playerList.add(testPlayer);
            testListException = new RankingImpl(playerList);
            fail("The list can't contain two players with the same name");
        } catch (DuplicatedPlayerStatsException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
        try {
            testPlayer.setPlayerName(PLAYER4);
            testPlayer.setTotalMatches(4);
            testPlayer.setWonMatches(5);
            fail("The won matches cant be more than the total matches");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }
    }
}
