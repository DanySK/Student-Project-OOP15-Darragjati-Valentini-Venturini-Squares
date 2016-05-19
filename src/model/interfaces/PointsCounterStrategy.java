package model.interfaces;

import model.exceptions.UnexistentLineListException;

/**
 * This interface is used to calculate the player's points after they have done
 * a move.
 */
public interface PointsCounterStrategy {

    /**
     * This method calculate the player points after an horizontal line is set.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            the line
     * @return the points scored
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    Integer horizontalPointScored(final Integer listIndex, final Integer position) throws UnexistentLineListException;

    /**
     * This method calculate the player points after an vertical line is set.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            the line
     * @return the points scored
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    Integer verticalPointScored(final Integer listIndex, final Integer position) throws UnexistentLineListException;

    /**
     * This method calculate the player points after an diagonal line is set.
     * 
     * @param listIndex
     *            is the number of the horizontal list where the player wants to
     *            set his line
     * @param position
     *            is the index of the chosen list where the player wants to set
     *            the line
     * @return the points scored
     * @throws UnsupportedOperationException
     *             if the grid doesn't support that action
     * @throws UnexistentLineListException
     *             if the listIndex input is not correct
     */
    Integer diagonalPointScored(final Integer listIndex, final Integer position) throws UnexistentLineListException;
}
