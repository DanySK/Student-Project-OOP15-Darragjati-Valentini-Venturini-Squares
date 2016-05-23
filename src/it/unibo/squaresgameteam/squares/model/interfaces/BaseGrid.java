package it.unibo.squaresgameteam.squares.model.interfaces;

/**
 * This interface has the base methods needed to create a new game mode. It must
 * be extended by other interfaces that should provide the methods to make
 * moves.
 */
public interface BaseGrid {

    /**
     * @return the number of moves
     */
    Integer getTotalMoves();

    /**
     * @return the number of moves left
     */
    Integer getRemainingMoves();

    /**
     * This method is used to know the maximum points that can be done in a
     * game.
     * 
     * @return the maximum points that can be done in this game
     */
    Integer getMatchMaximumPoints();
}
