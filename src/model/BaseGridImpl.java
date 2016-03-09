package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseGridImpl implements BaseGrid {

    private List<List<GridOption>> horizontal = new ArrayList<>();
    private List<List<GridOption>> vertical = new ArrayList<>();
    private GridOption turn = GridOption.EMPTY;
    private boolean matchStarted = false;
    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private final static Integer INITIAL_SCORE = 0;
    private final static Integer MINIMUM_SIZE = 4;
    private final static Integer MAXIMUM_SIZE = 10;

    public BaseGridImpl(Integer size) {

	if(size < MINIMUM_SIZE || size > MAXIMUM_SIZE){
	    throw new IllegalArgumentException();
	}
	for (int i = 0; i < size + 1; i++) {

	    vertical.add(createEmptyGrid(size));
	    horizontal.add(createEmptyGrid(size));
	}
	System.out.println("Orizzontale " + horizontal.toString() + "\nVerticale " + vertical.toString());

    }

    private List<GridOption> createEmptyGrid(Integer size) {

	List<GridOption> emptyGrid = new ArrayList<>();

	for (int i = 0; i < size; i++) {
	    emptyGrid.add(GridOption.EMPTY);
	}
	return emptyGrid;
    }

    @Override
    public void startMatch() {

	if(!isStarted()){
	    this.scorePlayer1 = INITIAL_SCORE;
	    this.scorePlayer2 = INITIAL_SCORE;

	    randomizeTurn();

	    matchStarted = true;
	} else {
	    throw new IllegalStateException();
	}
    }

    private void randomizeTurn() {

	if (!isStarted()) {
	    Random randomTurn = new Random();

	    if (randomTurn.nextInt(2) == 0) {
		this.turn = GridOption.PLAYER1;
	    } else {
		this.turn = GridOption.PLAYER2;
	    }
	} else {
	    throw new IllegalStateException();
	}

    }

    @Override
    public boolean isStarted() {
	return this.matchStarted;
    }

    @Override
    public boolean isEnded() {
	
	/*if(!isStarted()){
	    return false;
	}
	if(scorePlayer1 + scorePlayer2 < horizontal.get(0).size() * horizontal.get(0).size()){
	    return false;
	}
	return true;*/
	return (!isStarted() || (scorePlayer1 + scorePlayer2) < horizontal.get(0).size() * horizontal.get(0).size()) ? false : true;
    }

    @Override
    public GridOption getCurrentPlayerTurn() {

	if (isStarted()) {
	    return this.turn;
	} else {
	    throw new IllegalStateException();
	}
    }

    private void nextTurn() {

	if (!isStarted()) {
	    throw new IllegalStateException();
	}

	if (getCurrentPlayerTurn().equals(GridOption.PLAYER1)) {
	    this.turn = GridOption.PLAYER2;
	} else {
	    this.turn = GridOption.PLAYER1;
	}
    }

    @Override
    public Integer getPlayerPoints(GridOption player) {

	if (!isStarted()) {
	    return INITIAL_SCORE;
	}

	if(player.equals(GridOption.PLAYER1) || player.equals(GridOption.PLAYER2)){
	    return (player.equals(GridOption.PLAYER1)) ? scorePlayer1 : scorePlayer2;
	} else {
	    throw new IllegalArgumentException();
	}
	
	
    }

    @Override
    public Integer getTotalMoves() {

	return (horizontal.size() * horizontal.get(0).size()) + (vertical.size() * vertical.get(0).size());
    }
    
    @Override
    public Integer getRemainingMoves() {

	Integer movesLeft = 0;

	for (int i = 0; i < horizontal.size(); i++) {
	    for (GridOption grid : horizontal.get(i)) {
		if (grid.equals(GridOption.EMPTY)) {
		    movesLeft++;
		}
	    }
	}

	for (int i = 0; i < vertical.size(); i++) {
	    for (GridOption grid : vertical.get(i)) {
		if (grid.equals(GridOption.EMPTY)) {
		    movesLeft++;
		}
	    }
	}

	return movesLeft;
    }
    
    private boolean checkCorrectVerticalInput(Integer listIndex, Integer position){
	
	return (listIndex < 0 || listIndex > vertical.size() || position < 0 || position > vertical.get(0).size()) ? false : true;
    }

    @Override
    public GridOption getVerticalElement(Integer listIndex, Integer elementIndex) {
	
	if(!checkCorrectVerticalInput(listIndex, elementIndex)){
	    throw new IllegalArgumentException();
	}
	
	return vertical.get(listIndex).get(elementIndex);
    }

    @Override
    public void setVerticalLine(int listIndex, int position) {

	if(!checkCorrectVerticalInput(listIndex, position)){
	    throw new IllegalArgumentException();
	}
	
	if (vertical.get(listIndex).get(position).equals(GridOption.EMPTY)) {
	    vertical.get(listIndex).set(position, getCurrentPlayerTurn());

	    if (verticalPointScored(listIndex, position).equals(0)) {
		nextTurn();
	    }
	} else {
	    throw new IllegalStateException();
	}
    }

    private Integer verticalPointScored(int listIndex, int position) {

	int points = 0;

	if (listIndex != 0) {
	    if (getVerticalElement(listIndex - 1, position) != GridOption.EMPTY) {
		if (getHorizontalElement(position, listIndex - 1) != GridOption.EMPTY
			&& getHorizontalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
		    points++;
		}
	    }
	}

	if (listIndex != vertical.get(0).size()) {
	    if (getVerticalElement(listIndex + 1, position) != GridOption.EMPTY) {
		if (getHorizontalElement(position, listIndex) != GridOption.EMPTY
			&& getHorizontalElement(position + 1, listIndex) != GridOption.EMPTY) {
		    points++;
		}
	    }
	}
	addPoints(points);
	return points;
    }
    
    private boolean checkCorrectHorizontalInput(Integer listIndex, Integer position){
	
	return (listIndex < 0 || listIndex > horizontal.size() || position < 0 || position > horizontal.get(0).size()) ? false : true;
    }

    @Override
    public GridOption getHorizontalElement(Integer listIndex, Integer position) {
	
	if(!checkCorrectHorizontalInput(listIndex, position)){
	    throw new IllegalArgumentException();
	}
	
	return horizontal.get(listIndex).get(position);
    }

    @Override
    public void setHorizontalLine(int listIndex, int position) {

	if(!checkCorrectHorizontalInput(listIndex, position)){
	    throw new IllegalArgumentException();
	}
	
	if (horizontal.get(listIndex).get(position).equals(GridOption.EMPTY)) {
	    horizontal.get(listIndex).set(position, getCurrentPlayerTurn());

	    if (horizontalPointScored(listIndex, position).equals(0)) {
		nextTurn();
	    }
	} else {
	    throw new IllegalStateException();
	}
    }

    private Integer horizontalPointScored(int listIndex, int position) {

	int points = 0;

	if (listIndex != 0) {
	    if (getHorizontalElement(listIndex - 1, position) != GridOption.EMPTY) {
		if (getVerticalElement(position, listIndex - 1) != GridOption.EMPTY
			&& getVerticalElement(position + 1, listIndex - 1) != GridOption.EMPTY) {
		    points++;
		}
	    }
	}

	if (listIndex != horizontal.get(0).size()) {
	    if (getHorizontalElement(listIndex + 1, position) != GridOption.EMPTY) {
		if (getVerticalElement(position, listIndex) != GridOption.EMPTY
			&& getVerticalElement(position + 1, listIndex) != GridOption.EMPTY) {
		    points++;
		}
	    }
	}

	addPoints(points);
	return points;
    }

    private void addPoints(Integer points) {

	if (!isStarted()) {
	    throw new IllegalStateException();
	}

	if (getCurrentPlayerTurn().equals(GridOption.PLAYER1)) {
	    scorePlayer1 += points;
	} else {
	    scorePlayer2 += points;
	}
    }

    @Override
    public GridOption getWinner() {

	if (isEnded()) {
	    return (scorePlayer1 > scorePlayer2) ? GridOption.PLAYER1 : GridOption.PLAYER2;
	} else {
	    // da modificare non è quella giusta
	    throw new IllegalStateException();
	}
    }

}
