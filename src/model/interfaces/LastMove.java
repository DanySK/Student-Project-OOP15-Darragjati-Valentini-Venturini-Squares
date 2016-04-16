package model.interfaces;

import model.enumerations.ListType;

/**
 * 
 * 
 *
 */
public interface LastMove {

    /**
     * 
     * @return the last list used, it is respectively ListType.HORIZONTAL or
     *         ListType.VERTICAL if it is the horizontal or the vertical one
     */
    ListType getLastListType();

    /**
     * 
     * @param lastListType the type of the last list used
     */
    void setLastListType(ListType lastListType);

    /**
     * 
     * @return the number of the "n" list was used last
     */
    Integer getLastListIndex();

    /**
     * 
     * @param lastListIndex which one of the "n" lists is used last
     */
    void setLastListIndex(Integer lastListIndex);

    /**
     * 
     * @return the last index used to insert a move in the list
     */
    Integer getLastPosition();

    /**
     * 
     * @param lastPosition the index of the list where was insert the last move
     */
    void setLastPosition(Integer lastPosition);

}
