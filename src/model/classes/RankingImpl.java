package model.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.interfaces.Ranking;

public class RankingImpl implements Ranking {

    Map<String, PlayerData<Double, Integer, Integer, Integer>> oldRanking = new HashMap<>();
    private List<String> oldRanking2 = new ArrayList<>();
    private List<Pair<String, Integer>> updatedRanking = new ArrayList<>();

    public RankingImpl( Map<String, PlayerData<Double, Integer, Integer, Integer>> oldRanking) {
	this.oldRanking = oldRanking;
    }
    
    public void method(){
	
    }
    
    @Override
    public void addPlayerResults(String playerName, boolean win, Integer score) {

	if (oldRanking.containsKey(playerName)) {
		update(playerName, win, score);
		return;
	    }
	
	addNewPlayer(playerName, win, score);
    }

    private void addNewPlayer(String playerName, boolean win, Integer score) {
/*
	boolean playerAdded = false;
	for (Pair<String, Integer> p : oldRanking) {
	    if (p.getY() < score && !playerAdded) {
		updatedRanking.add(new Pair<>(playerName, score));
		playerAdded = true;
	    }
	    updatedRanking.add(p);
	}

	if (!playerAdded) {
	    updatedRanking.add(new Pair<>(playerName, score));
	}*/
    }

    private void update(String playerName, boolean win, Integer score) {

	PlayerData<Double, Integer, Integer, Integer> playerScores = new PlayerData<>();
	if(win){
	    playerScores.setTotalWins(oldRanking.get(playerName).getTotalWins()+1);
	}
	playerScores.setTotalMatches(oldRanking.get(playerName).getTotalMatches()+1);
	playerScores.setWinrate((double) (playerScores.getTotalWins()/playerScores.getTotalMatches()));
	playerScores.setSquareCatched(oldRanking.get(playerName).getSquareCatched()+score);
	oldRanking.replace(playerName, playerScores);
	

    }

}
