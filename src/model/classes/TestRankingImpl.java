package model.classes;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestRankingImpl {

    private static final String PLAYER1 = "Pippo";
    private static final String PLAYER2 = "Pluto";
    private static final String PLAYER3 = "Paperino";
    @Test
    public void test() {
        
        Map<String, PlayerData<Double, Integer, Integer, Integer>> rankingTest = new HashMap<>();
        PlayerData<Double, Integer, Integer, Integer> playerDataTest = new PlayerData<>();
        playerDataTest.setTotalWins(1);
        playerDataTest.setTotalMatches(2);
        playerDataTest.setSquareCatched(33);
        playerDataTest.setWinrate((double) (playerDataTest.getTotalWins()/playerDataTest.getTotalMatches()));
        rankingTest.put(PLAYER1, playerDataTest);
    }
}
