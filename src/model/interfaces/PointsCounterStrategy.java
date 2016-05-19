package model.interfaces;

public interface PointsCounterStrategy {

    Integer horizontalPointScored(final Integer listIndex, final Integer position);
    
    Integer verticalPointScored(final Integer listIndex, final Integer position);
}
