package model.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import model.enumerations.RankingOption;

public class TestRankingImpl {

    private static final String PLAYER1 = "Pippo";
    private static final String PLAYER2 = "Pluto";
    private static final String PLAYER3 = "Paperino";
    private static final String PLAYER4 = "Topolino";
    private static final String PLAYER5 = "Paperone";

    @Test
    public void test() {

        List<PlayerImpl> playerList = new ArrayList<>();
        PlayerImpl player1 = new PlayerImpl(PLAYER1);
        PlayerImpl player2 = new PlayerImpl(PLAYER2);
        PlayerImpl player3 = new PlayerImpl(PLAYER3);

        player1.setWonMatches(1);
        player1.setTotalMatches(10);
        player1.setTotalSquaresCatched(33);
        assertTrue(player1.getWinRate() == 0.1);

        player2.setWonMatches(8);
        player2.setTotalMatches(10);
        player2.setTotalSquaresCatched(456);
        assertTrue(player2.getWinRate() > player1.getWinRate());

        player3.setWonMatches(4);
        player3.setTotalMatches(5);
        player3.setTotalSquaresCatched(223);
        assertTrue(player3.getWinRate() == player2.getWinRate());

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);

        assertEquals(playerList.size(), 3);

        RankingImpl rankingTest = new RankingImpl(playerList);

        rankingTest.addPlayerResults(PLAYER4, true, 37);

        assertEquals(playerList.size(), 4);

        List<PlayerImpl> testWinRateOrderedList = rankingTest.orderListBy(RankingOption.WINRATE);

        assertEquals(testWinRateOrderedList.get(0).getPlayerName(), PLAYER4);
        assertEquals(testWinRateOrderedList.get(1).getPlayerName(), PLAYER2);
        assertEquals(testWinRateOrderedList.get(2).getPlayerName(), PLAYER3);
        assertEquals(testWinRateOrderedList.get(3).getPlayerName(), PLAYER1);

        List<PlayerImpl> testWonMatchesOrderedList = rankingTest.orderListBy(RankingOption.TOTAL_WINS);

        assertEquals(testWonMatchesOrderedList.get(0).getPlayerName(), PLAYER2);
        assertEquals(testWonMatchesOrderedList.get(1).getPlayerName(), PLAYER3);
        assertEquals(testWonMatchesOrderedList.get(2).getPlayerName(), PLAYER4);
        assertEquals(testWonMatchesOrderedList.get(3).getPlayerName(), PLAYER1);

        List<PlayerImpl> testPlayedMatchesOrderedList = rankingTest.orderListBy(RankingOption.TOTAL_MATCHES);

        assertEquals(testPlayedMatchesOrderedList.get(0).getPlayerName(), PLAYER2);
        assertEquals(testPlayedMatchesOrderedList.get(1).getPlayerName(), PLAYER1);
        assertEquals(testPlayedMatchesOrderedList.get(2).getPlayerName(), PLAYER3);
        assertEquals(testPlayedMatchesOrderedList.get(3).getPlayerName(), PLAYER4);

        List<PlayerImpl> testTotalSquaresCatchedOrderedList = rankingTest
                .orderListBy(RankingOption.TOTAL_SQUARES_CATCHED);

        assertEquals(testTotalSquaresCatchedOrderedList.get(0).getPlayerName(), PLAYER2);
        assertEquals(testTotalSquaresCatchedOrderedList.get(1).getPlayerName(), PLAYER3);
        assertEquals(testTotalSquaresCatchedOrderedList.get(2).getPlayerName(), PLAYER4);
        assertEquals(testTotalSquaresCatchedOrderedList.get(3).getPlayerName(), PLAYER1);

        rankingTest.addPlayerResults(PLAYER5, true, 37);

        List<PlayerImpl> testWinRateOrderedList2 = rankingTest.orderListBy(RankingOption.WINRATE);

        assertEquals(testWinRateOrderedList2.get(0).getPlayerName(), PLAYER5);
        assertEquals(testWinRateOrderedList2.get(1).getPlayerName(), PLAYER4);
        assertEquals(testWinRateOrderedList2.get(2).getPlayerName(), PLAYER2);
        assertEquals(testWinRateOrderedList2.get(3).getPlayerName(), PLAYER3);
        assertEquals(testWinRateOrderedList2.get(4).getPlayerName(), PLAYER1);

        List<PlayerImpl> testReverseOrderedList = rankingTest.reverseRanking(RankingOption.WINRATE);

        System.out.println(testReverseOrderedList.size());
        assertEquals(testReverseOrderedList.get(0).getPlayerName(), PLAYER1);
        assertEquals(testReverseOrderedList.get(1).getPlayerName(), PLAYER3);
        assertEquals(testReverseOrderedList.get(2).getPlayerName(), PLAYER2);
        assertEquals(testReverseOrderedList.get(3).getPlayerName(), PLAYER4);

    }
    
    @Test
    public void testExceptions() {
        
        List<PlayerImpl> playerList = new ArrayList<>();
        PlayerImpl player1 = new PlayerImpl(PLAYER1);
        PlayerImpl player2 = new PlayerImpl(PLAYER2);
        PlayerImpl player3 = new PlayerImpl(PLAYER3);

        player1.setWonMatches(1);
        player1.setTotalMatches(10);
        player1.setTotalSquaresCatched(33);

        player2.setWonMatches(8);
        player2.setTotalMatches(10);
        player2.setTotalSquaresCatched(456);
        
        player3.setWonMatches(4);
        player3.setTotalMatches(5);
        player3.setTotalSquaresCatched(223);
        
        playerList.add(player1);
        playerList.add(player2);
        
        RankingImpl testListException;
        
        try{
            testListException = new RankingImpl(playerList);
        } catch (Exception e){
            
        }
        /*
        try{
            testListException.orderListBy(RankingOption.WINRATE).add(player3);
        } catch (UnsupportedOperationException e){
        } catch (Exception e) {
            fail("Wrong exception thrown");
        }*/
   
        
        playerList.add(player1);
        
        try{
            testListException = new RankingImpl(playerList);
        } catch (Exception e){
        }
    }
}
