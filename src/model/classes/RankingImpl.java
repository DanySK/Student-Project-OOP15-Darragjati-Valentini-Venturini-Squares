package model.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.enumerations.RankingOption;
import model.interfaces.Ranking;

public class RankingImpl implements Ranking {

    private Map<String, PlayerData<Double, Integer, Integer, Integer>> oldRanking = new HashMap<>();
    private List<Pair<String, PlayerData<Double, Integer, Integer, Integer>>> updatedRanking;
    private static final Integer WINRATE_CODE = 1;
    private static final Integer WINS_CODE = 2;
    private static final Integer TOTAL_MATCHES_CODE = 3;
    private static final Integer TOTAL_SQUARES_CATCHED_CODE = 4;

    public RankingImpl(Map<String, PlayerData<Double, Integer, Integer, Integer>> oldRanking) {
        this.oldRanking = oldRanking;
    }

    @Override
    public void addPlayerResults(String playerName, boolean win, Integer score) {

        if (oldRanking.containsKey(playerName)) {
            updatePlayerResults(playerName, win, score);
            return;
        }

        addNewPlayer(playerName, win, score);
    }

    private void addNewPlayer(String playerName, boolean win, Integer score) {

        PlayerData<Double, Integer, Integer, Integer> playerScores = new PlayerData<>();
        if (win) {
            playerScores.setTotalWins(1);
        }
        playerScores.setTotalMatches(1);
        playerScores.setWinrate((double) (playerScores.getTotalWins() / playerScores.getTotalMatches()));
        playerScores.setSquareCatched(score);
        oldRanking.put(playerName, playerScores);
    }

    private void updatePlayerResults(String playerName, boolean win, Integer score) {

        PlayerData<Double, Integer, Integer, Integer> playerScores = new PlayerData<>();
        if (win) {
            playerScores.setTotalWins(oldRanking.get(playerName).getTotalWins() + 1);
        }
        playerScores.setTotalMatches(oldRanking.get(playerName).getTotalMatches() + 1);
        playerScores.setWinrate((double) (playerScores.getTotalWins() / playerScores.getTotalMatches()));
        playerScores.setSquareCatched(oldRanking.get(playerName).getSquareCatched() + score);
        oldRanking.replace(playerName, playerScores);
    }

    public List<Pair<String, PlayerData<Double, Integer, Integer, Integer>>> orderListBy(RankingOption option) {

        updatedRanking = new ArrayList<>();
        for (String player : oldRanking.keySet()) {

            if (updatedRanking.size() == 0) {
                updatedRanking.add(createNewPlayerList(player));
            } else {
                switch (option) {
                case WINRATE:
                    break;
                case TOTAL_WINS:
                    break;
                case TOTAL_MATCHES:
                    break;
                case TOTAL_SQUARES_CATCHED:
                    break;
                default:
                    throw new IllegalStateException("There is a bug here.");
                }
                for (int i = 0; i < updatedRanking.size(); i++) {
                    if (oldRanking.get(player).getWinRate() > updatedRanking.get(i).getY().getWinRate()) {
                        updatedRanking.add(i, createNewPlayerList(player));
                        break;
                    }
                }

            }
        }

        return Collections.unmodifiableList(updatedRanking);
    }

    private Pair<String, PlayerData<Double, Integer, Integer, Integer>> createNewPlayerList(String player) {

        PlayerData<Double, Integer, Integer, Integer> datas = new PlayerData<>();
        datas.setWinrate(oldRanking.get(player).getWinRate());
        datas.setTotalWins(oldRanking.get(player).getTotalWins());
        datas.setTotalMatches(oldRanking.get(player).getTotalMatches());
        datas.setSquareCatched(oldRanking.get(player).getSquareCatched());

        return new Pair<>(player, datas);
    }
}
