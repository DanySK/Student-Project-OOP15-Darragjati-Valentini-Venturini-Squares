package model.interfaces;

import model.enumerations.ListType;

/**
 * This interface is used to organize the last move done by one of the two
 * players through getters and setters.
 */
public interface Move {

    /**
     * Gets wich one of the two list types was used last.
     * @return the last list used, it is respectively ListType.HORIZONTAL or
     *         ListType.VERTICAL if it is the horizontal or the vertical one
     */
    ListType getLastListType();

    /**
     * Sets wich one of the two list types was used last.
     * @param lastListType
     *            the type of the last list used
     */
    void setLastListType(ListType lastListType);

    /**
     * Gets wich one of the "n" lists was used last.
     * @return the number of the "n" list was used last
     */
    Integer getLastListIndex();

    /**
     * Sets wich one of the "n" lists was used last.
     * @param lastListIndex
     *            which one of the "n" lists is used last
     */
    void setLastListIndex(Integer lastListIndex);

    /**
     * Gets the last index used to make a move.
     * @return the last index used to make a move
     */
    Integer getLastPosition();

    /**
     * Sets the last index used to make a move.
     * @param lastPosition
     *            the index of the list where was insert the last move
     */
    void setLastPosition(Integer lastPosition);

}
