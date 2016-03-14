package model.classes;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Ranking;

public class RankingImpl implements Ranking{

    List<Pair<String, Integer>> oldRanking = new ArrayList<>();
    List<Pair<String, Integer>> rank = new ArrayList<>();

    public RankingImpl(List<Pair<String, Integer>> oldRanking) {
	this.oldRanking = oldRanking;
    }
    
    @Override
    public void addPlayerResults(String playerName, Integer score) {
	
	if(!rank.contains(playerName)){
	    Pair<String, Integer> playerStats = new Pair<>(playerName, score);
	    rank.add(playerStats);
	} else {
	    update(playerName, score);
	}
    }
    
    private void update(String playername, Integer score){
	
    }
    
}
