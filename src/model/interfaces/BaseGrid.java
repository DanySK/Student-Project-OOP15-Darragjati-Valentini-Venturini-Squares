package model.interfaces;

import model.enumerations.GridOption;

public interface BaseGrid {

    /**
     * Starts a new match
     * @throws a IllegalStateException if the match is already started
     */
    public void startMatch();

    /**
     * 
     * @return true if the game is started
     */
    public boolean isStarted();

    /**
     * 
     * @return if there are no more moves left
     */
    public boolean isEnded();

    /**
     * 
     * @return the player who should take the next move
     * @throws a IllegalStateException if the match is not started
     */
    public GridOption getCurrentPlayerTurn();

    /**
     * Gets the score of one of the two players
     * @param player
     * @return his points
     * @throws a IllegalArgumentException if player does not exist
     */
    public Integer getPlayerPoints(GridOption player);

    /**
     * 
     * @return the number of moves
     */
    public Integer getTotalMoves();

    /**
     * 
     * @return the number of moves left
     */
    public Integer getRemainingMoves();

    /**
     * 
     * @param listIndex is the number of list
     * @param elementIndex is the position of the chosen list where the player wants to set the line
     * @return which player has set the vertical line, if it is empty noone has set that line
     * @throws a IllegalArgumentException if the parameters insert are not correct
     */
    public GridOption getVerticalElement(Integer listIndex, Integer elementIndex);

    /**
     * Makes a move setting a vertical line in the grid
     * @param listIndex is the number of the list where the player wants to set his line
     * @param elementIndex is the position of the chosen list where the player wants to set the line
     * @throws a IllegalArgumentException if the parameters insert are not correct
     */
    public void setVerticalLine(int listIndex, int position);

    /**
     * 
     * @param listIndex is the number of list
     * @param elementIndex is the position of the chosen list where the player wants to set the line
     * @return which player has set the horizontal line, if it is empty noone has set that line
     * @throws a IllegalArgumentException if the parameters insert are not correct
     */
    public GridOption getHorizontalElement(Integer listIndex, Integer position);
	
    /**
     * Makes a move setting a horizontal line in the grid
     * @param listIndex is the number of the list where the player wants to set his line
     * @param elementIndex is the position of the chosen list where the player wants to set the line
     * @throws a IllegalArgumentException if the parameters insert are not correct
     */
    public void setHorizontalLine(int listIndex, int position);

    /**
     * 
     * @return the winner of the match, if the game is even returns GridOption.Empty
     * @throws a IllegalStateException if the game is not ended
     */
    public GridOption getWinner();

 
}
