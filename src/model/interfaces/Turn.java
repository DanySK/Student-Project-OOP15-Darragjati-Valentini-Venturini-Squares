package model.interfaces;

import model.enumerations.GridOption;
import model.enumerations.ListType;

public interface Turn extends BaseGrid{

    /**
     * Starts a new match.
     * @throws a IllegalStateException if the match is already started
     */
    void startMatch();

    /**
     * 
     * @return true if the game is started
     */
    boolean isStarted();

    /**
     * 
     * @return if there are no more moves left
     */
    boolean isEnded();

    /**
     * 
     * @return the player who should take the next move
     * @throws a IllegalStateException if the match is not started
     */
    GridOption getCurrentPlayerTurn();

    /**
     * Gets the score of one of the two players.
     * @param player is one of the players
     * @return his points
     * @throws a IllegalArgumentException if player does not exist
     */
    Integer getPlayerPoints(GridOption player);


    /**
     * Makes a move setting a line in the grid.
     * @param 
     * @param listIndex is the number of the list where the player wants to set his line
     * @param position is the position of the chosen list where the player wants to set the line
     * @throws a IllegalArgumentException if the parameters insert are not correct
     */
    void setLine(ListType list, Integer listIndex, Integer position);

    /**
     * 
     * @return the winner of the match, if the game is even returns GridOption.Empty
     * @throws a IllegalStateException if the game is not ended
     */
    GridOption getWinner();
}
