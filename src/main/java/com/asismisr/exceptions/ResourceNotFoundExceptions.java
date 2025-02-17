package com.asismisr.exceptions;

/**
 * ResourceNotFoundExceptions will only handle the cases where Resources are invalid(wrong path, file not found, file corrupted....)
 */

public class ResourceNotFoundExceptions extends FrameworkExceptions{

    /**
     * ResourceNotFoundExceptions with only message
     * @param message: error/stack trace message
     */
    public ResourceNotFoundExceptions(String message){
        super(message);
    }

    /**
     * ResourceNotFoundExceptions with custom message and throwable
     * @param message: error/stack trace message
     * @param cause: can be some throwable
     */
    public ResourceNotFoundExceptions(String message, Throwable cause){
        super(message, cause);
    }
}
