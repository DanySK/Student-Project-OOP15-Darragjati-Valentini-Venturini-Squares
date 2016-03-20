package model.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.interfaces.Ranking;

public class RankingImpl implements Ranking {

    private Map<String, PlayerData<Double, Integer, Integer, Integer>> oldRanking = new HashMap<>();
    private List<List<String>> updatedRanking = new ArrayList<>();

    public RankingImpl( Map<String, PlayerData<Double, Integer, Integer, Integer>> oldRanking) {
	this.oldRanking = oldRanking;
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

	PlayerData<Double, Integer, Integer, Integer> playerScores = new PlayerData<>();
	if(win){
	    playerScores.setTotalWins(1);
	}
	playerScores.setTotalMatches(1);
	playerScores.setWinrate((double) (playerScores.getTotalWins()/playerScores.getTotalMatches()));
	playerScores.setSquareCatched(score);
	oldRanking.put(playerName, playerScores);
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
    
    public List<List<String>> orderListByWinrate(){
        
        for(String player : oldRanking.keySet()){
            
            List<String> newPlayer = new ArrayList<>();
            newPlayer.add(player);
            newPlayer.add("" + oldRanking.get(player).getWinRate());
            newPlayer.add("" + oldRanking.get(player).getTotalWins());
            newPlayer.add("" + oldRanking.get(player).getTotalMatches());
            newPlayer.add("" + oldRanking.get(player).getSquareCatched());
            
            if(updatedRanking.size()==0){
                updatedRanking.add(new ArrayList<String>());
            }
            
            oldRanking.get(player).getWinRate();
        }
         
        //mettere copyof
        return updatedRanking;
    }

}
