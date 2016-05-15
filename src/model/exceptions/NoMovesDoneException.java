package model.exceptions;

public class NoMovesDoneException extends Exception {
    
    private static final long serialVersionUID = -3106890178914633902L;

    /**
     * 
     */
    public NoMovesDoneException() {
        super();
    }
    
    /**
     * 
     * @param cause
     */
    public NoMovesDoneException(final Throwable cause) {
        super(cause);
    }
}
