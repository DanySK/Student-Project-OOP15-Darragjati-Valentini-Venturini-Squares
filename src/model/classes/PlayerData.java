package model.classes;

public class PlayerData<A, B, C, D> {

    A winRate;
    B matchesWon;
    C totalMatches;
    D squareCatched;

    
    public A getWinRate() {
	return winRate;
    }

    public void setWinrate(A winRate) {
	this.winRate = winRate;
    }
    
    public B getTotalWins() {
	return matchesWon;
    }

    public void setTotalWins(B matchesWon) {
	this.matchesWon = matchesWon;
    }

    public C getTotalMatches() {
	return totalMatches;
    }

    public void setTotalMatches(C totalMatches) {
	this.totalMatches = totalMatches;
    }

    public D getTotalSquaresCatched() {
	return squareCatched;
    }

    public void setTotalSquaresCatched(D squareCatched) {
	this.squareCatched = squareCatched;
    }
}
