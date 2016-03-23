package model.classes;

public class Player {
 
    private String playerName;
    private Integer score;
    private Integer win;
    private Integer totalMatches;
    private Integer totalSquaresCatched;
    private double winRate;
   
    
    public Player(String playerName){
	
	this.playerName = playerName;
	this.score = 0;
	this.win = 0;
	this.totalMatches = 0;
	this.winRate = 0;
    }
    
    public String getPlayerName(){
	return playerName;
    }
    
    public Integer getPlayerScore(){
	return this.score;
    }
    
    public Integer getTotalWins(){
	return this.win;
    }
    
    public Integer getTotalMatches(){
	return this.totalMatches;
    }
    
    public double getWinRate() {
	return this.winRate;
    }
    
    public void addLastMatchResults(Integer score, boolean victory, Integer totalSquaresCatched){
	
	this.score += score;
	
	if(victory){
	    this.win++;
	}
	
	this.totalMatches++;
	
	calculateWinRate();
	
	this.totalSquaresCatched += totalSquaresCatched;
    }
    
    private void calculateWinRate(){
	
	this.winRate = getTotalWins() / getTotalMatches();
    }
}
