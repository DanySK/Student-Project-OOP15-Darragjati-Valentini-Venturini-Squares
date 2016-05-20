package it.unibo.squaresgameteam.squares.model.classes;

import it.unibo.squaresgameteam.squares.model.enumerations.ListType;
import it.unibo.squaresgameteam.squares.model.interfaces.Move;

/**
 * This class implements the interface Move and it is used to set the necessary
 * fields to make a move.
 */
public class MoveImpl implements Move {

    private ListType listType;
    private Integer listIndex;
    private Integer position;

    /**
     * This constructor sets the fields of the object.
     * @param listType the type of the list where a player wants to set a move
     * @param listIndex the number of the list where a player wants to set a move
     * @param position the position where a player wants to set a move 
     */
    //CHECKSTYLE:OFF:
    public MoveImpl(final ListType listType, final Integer listIndex, final Integer position) {
      //CHECKSTYLE:ON:
        this.listType = listType;
        this.listIndex = listIndex;
        this.position = position;
    }

    @Override
    public ListType getListType() {
        return listType;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setListType(final ListType lastListType) {
        // CHECKSTYLE:ON:
        this.listType = lastListType;
    }

    @Override
    public Integer getListIndex() {
        return listIndex;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setListIndex(final Integer lastListIndex) {
        // CHECKSTYLE:ON:
        this.listIndex = lastListIndex;
    }

    @Override
    public Integer getPosition() {
        return position;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setPosition(final Integer lastPosition) {
        // CHECKSTYLE:ON:
        this.position = lastPosition;
    }

}
