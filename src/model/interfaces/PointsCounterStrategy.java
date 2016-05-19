package model.interfaces;

import model.exceptions.UnexistentLineListException;

public interface PointsCounterStrategy {

    Integer horizontalPointScored(final Integer listIndex, final Integer position) throws UnexistentLineListException;
    
    Integer verticalPointScored(final Integer listIndex, final Integer position) throws UnexistentLineListException;
    
    Integer diagonalPointScored(final Integer listIndex, final Integer position) throws UnexistentLineListException;
}
