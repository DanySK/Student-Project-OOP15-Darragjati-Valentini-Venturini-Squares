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
    public ListType getLastListType() {
        return lastListType;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setLastListType(final ListType lastListType) {
        // CHECKSTYLE:ON:
        this.lastListType = lastListType;
    }

    @Override
    public Integer getLastListIndex() {
        return lastListIndex;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setLastListIndex(final Integer lastListIndex) {
        // CHECKSTYLE:ON:
        this.lastListIndex = lastListIndex;
    }

    @Override
    public Integer getLastPosition() {
        return lastPosition;
    }

    @Override
    // CHECKSTYLE:OFF:
    public void setLastPosition(final Integer lastPosition) {
        // CHECKSTYLE:ON:
        this.lastPosition = lastPosition;
    }

}
