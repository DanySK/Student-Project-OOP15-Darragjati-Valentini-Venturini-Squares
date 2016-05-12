package model.classes;

import model.enumerations.ListType;
import model.interfaces.Move;

/**
 * 
 * @author Turi
 *
 */
public class MoveImpl implements Move {

    private ListType lastListType;
    private Integer lastListIndex;
    private Integer lastPosition;

    /**
     * This constructor sets the fields of the object.
     */
    public MoveImpl() {
        this.lastListIndex = -1;
        this.lastPosition = -1;
    }

    @Override
    public ListType getListType() {
        return lastListType;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setListType(final ListType lastListType) {
        // CHECKSTYLE:ON:
        this.lastListType = lastListType;
    }

    @Override
    public Integer getListIndex() {
        return lastListIndex;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setListIndex(final Integer lastListIndex) {
        // CHECKSTYLE:ON:
        this.lastListIndex = lastListIndex;
    }

    @Override
    public Integer getPosition() {
        return lastPosition;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setPosition(final Integer lastPosition) {
        // CHECKSTYLE:ON:
        this.lastPosition = lastPosition;
    }

}
