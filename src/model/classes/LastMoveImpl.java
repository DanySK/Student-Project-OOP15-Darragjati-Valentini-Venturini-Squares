package model.classes;

import model.enumerations.ListType;
import model.interfaces.LastMove;

public class LastMoveImpl implements LastMove{

    private ListType lastListType;
    private Integer lastListIndex;
    private Integer lastPosition;

    private LastMoveImpl() {
    }

    public ListType getLastListType() {
        return lastListType;
    }

    public void setLastListType(final ListType lastListType) {
        this.lastListType = lastListType;
    }

    public Integer getLastListIndex() {
        return lastListIndex;
    }

    public void setLastListIndex(final Integer lastListIndex) {
        this.lastListIndex = lastListIndex;
    }

    public Integer getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(final Integer lastPosition) {
        this.lastPosition = lastPosition;
    }

}
