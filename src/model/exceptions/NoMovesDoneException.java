package model.exceptions;

/**
 * This exception is invoked when the method undo is called and noone has done a
 * move yet.
 */
public class NoMovesDoneException extends RuntimeException {

    private static final long serialVersionUID = -3106890178914633902L;

}
