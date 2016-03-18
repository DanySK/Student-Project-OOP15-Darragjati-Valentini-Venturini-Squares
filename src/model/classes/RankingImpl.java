package model.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.interfaces.Ranking;

public class RankingImpl implements Ranking {

    Map<String, PlayerData<Integer, Integer, Integer>> map = new TreeMap<>();
    private List<String> oldRanking = new ArrayList<>();
    private List<Pair<String, Integer>> updatedRanking = new ArrayList<>();

    public RankingImpl(List<List<String>> oldRanking) {
	this.oldRanking = oldRanking;
    }
    
    public void method(){
	
    }
    
    @Override
    public void addPlayerResults(String playerName, boolean win, Integer score) {
/*
	for (Pair<String, Integer> p : oldRanking) {
	    if (p.getX().equals(playerName)) {
		update(playerName, score);
		return;
	    }
	}
	addNewPlayer(playerName, score);*/
    }

    private void addNewPlayer(String playerName, Integer score) {
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

    private void update(String playerName, Integer score) {
/*
	for (Pair<String, Integer> p : oldRanking) {
	    if (p.getX().equals(playerName)) {
		updatedRanking.add(new Pair<>(playerName, p.getY() + score));
	    } else {
		updatedRanking.add(p);
	    }
	}*/
    }

}
