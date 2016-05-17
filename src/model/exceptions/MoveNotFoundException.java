package model.exceptions;

/**
 * This exception is thrown when the method undo is called and no moves are done
 * in the selected position.
 */
public class MoveNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8851420793026002772L;
}
