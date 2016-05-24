package it.unibo.squaresgameteam.squares.model.interfaces;

/**
 * This interface is used get a player's points after he has done a move.
 */
public interface PointsCounterStrategy {

    /**
     * This method is used to know if a move has scored a point.
     * 
     * @return the points scored
     */
    Integer getPoints();
}
