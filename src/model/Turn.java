package model;

import java.util.Random;

public class Turn {

    private boolean matchStarted = false;
    Integer scorePlayer1;
    Integer scorePlayer2;
    private GridOption turn;
    public BaseGrid base;
    
    
    public void startMatch() {
	
	this.scorePlayer1 = 0;
	this.scorePlayer2 = 0;

	randomizeTurn();
	matchStarted = true;
    }
    
    private void randomizeTurn(){
	Random randomTurn = new Random();
	int r = randomTurn.nextInt(2);
	System.out.println(r);
	if(randomTurn.nextInt(2) == 0){
	    this.turn = GridOption.PLAYER1;
	} else {
	    this.turn = GridOption.PLAYER2;
	}
	
    }

    public boolean isStarted() {
	return this.matchStarted;
    }

    public boolean isEnded() {
	return (isStarted() || (scorePlayer1 + scorePlayer2) < base.getTotalMoves()) ? false : true;
    }
    
    public GridOption getCurrentTurn(){
	return this.turn;
    }

    public void nextTurn(GridOption currentTurn) {
	
	if(currentTurn.equals(GridOption.PLAYER1)){
	    this.turn = GridOption.PLAYER2;
	} else {
	    this.turn = GridOption.PLAYER1;
	}
    }
    
    public Integer getPlayerPoints(GridOption player){
	if(player.equals(GridOption.PLAYER1)){
	    return scorePlayer1;
	}
    	return scorePlayer2;
    }
    
    

}
