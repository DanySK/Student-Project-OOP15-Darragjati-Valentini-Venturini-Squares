package model.classes;

import model.enumerations.ListType;
import model.interfaces.LastMove;

public class LastMoveImpl implements LastMove{

    private ListType lastListType;
    private Integer lastListIndex;
    private Integer lastPosition;

    public LastMoveImpl() {
        this.lastListIndex = -1;
        this.lastPosition = -1;
    }

    @Override
    public ListType getLastListType() {
        return lastListType;
    }

    @Override
    public void setLastListType(final ListType lastListType) {
        this.lastListType = lastListType;
    }

    @Override
    public Integer getLastListIndex() {
        return lastListIndex;
    }

    @Override
    public void setLastListIndex(final Integer lastListIndex) {
        this.lastListIndex = lastListIndex;
    }

    @Override
    public Integer getLastPosition() {
        return lastPosition;
    }

    @Override
    public void setLastPosition(final Integer lastPosition) {
        this.lastPosition = lastPosition;
    }

}
