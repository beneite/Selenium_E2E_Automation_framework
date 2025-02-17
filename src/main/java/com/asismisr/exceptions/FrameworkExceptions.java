package com.asismisr.exceptions;

/**
 * this class handles the generic framework level exceptions and throw them as runtime exception which will terminate the program instantly.
 * We could have used checked exception but checked exception does not terminate the program
 */

public class FrameworkExceptions extends RuntimeException{

    /**
     * this method can be used to throw exception with a message
     * @param message: error/stack trace message
     */
    public FrameworkExceptions(String message){
        super(message);
    }

    /**
     * this method can be used to throw exception with message and throwable
     * @param message: error/stack trace message
     * @param cause: can be some throwable
     */
    public FrameworkExceptions(String message, Throwable cause){
        super(message, cause);
    }
}
