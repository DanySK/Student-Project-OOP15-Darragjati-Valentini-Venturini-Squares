package it.unibo.squaresgameteam.squares.model.exceptions;

/**
 * This exception is thrown when a player tries to insert a move that was done
 * by another one.
 */
public class MoveAlreadyDoneException extends RuntimeException {

    private static final long serialVersionUID = -1317069819709809888L;
    
    /**
     * 
     * @param string the message to show if the method is called
     */
    public MoveAlreadyDoneException(final String string) {
        super(string);
    }
}
