package it.unibo.squaresgameteam.squares.model.interfaces;

import it.unibo.squaresgameteam.squares.model.enumerations.ListType;

/**
 * This interface is used to organize move done by one of the two
 * players through getters and setters.
 */
public interface Move {

    /**
     * @return the list used to make a move
     */
    ListType getListType();

    /**
     * @param lastListType
     *            the type of the list used to make the move
     */
    void setListType(ListType lastListType);

    /**
     * @return wich one of the "n" list is used make a move
     */
    Integer getListIndex();

    /**
     * @param lastListIndex
     *            wich one of the "n" list is used make a move
     */
    void setListIndex(Integer lastListIndex);

    /**
     * @return the index of the list used to make a move
     */
    Integer getPosition();

    /**
     * @param lastPosition
     *            the index of the list used to make a move
     */
    void setPosition(Integer lastPosition);

}
