package model.classes;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestRankingImpl {

    private static final String PLAYER1 = "Pippo";
    private static final String PLAYER2 = "Pluto";
    private static final String PLAYER3 = "Paperino";
  
    @Test
    public void test() {
        
        Map<String, PlayerData<Double, Integer, Integer, Integer>> oldRanking = new HashMap<>();
        PlayerData<Double, Integer, Integer, Integer> playerDataTest = new PlayerData<>();
       
        playerDataTest.setTotalWins(1);
        playerDataTest.setTotalMatches(2);
        playerDataTest.setTotalSquaresCatched(33);
        double winrate = playerDataTest.getTotalWins()/playerDataTest.getTotalMatches();
        playerDataTest.setWinrate(winrate);
        oldRanking.put(PLAYER1, playerDataTest);
       
        playerDataTest.setTotalWins(8);
        playerDataTest.setTotalMatches(10);
        playerDataTest.setTotalSquaresCatched(456);
        winrate = playerDataTest.getTotalWins()/playerDataTest.getTotalMatches();
        playerDataTest.setWinrate(winrate);
        oldRanking.put(PLAYER2, playerDataTest);
        
        playerDataTest.setTotalWins(4);
        playerDataTest.setTotalMatches(5);
        playerDataTest.setTotalSquaresCatched(223);
        winrate = playerDataTest.getTotalWins()/playerDataTest.getTotalMatches();
        playerDataTest.setWinrate(winrate);
        oldRanking.put(PLAYER3, playerDataTest);
        
        assertEquals(oldRanking.size(), 3);
        
       /* RankingImpl rankingTest = new RankingImpl(oldRanking);
        
        rankingTest.addPlayerResults(PLAYER1, false, 13);
        rankingTest.addPlayerResults("Topolino", false, 22);*/
        System.out.println("BENE!");
        
    }
}
