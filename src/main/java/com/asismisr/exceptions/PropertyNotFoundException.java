package com.asismisr.exceptions;

public class PropertyNotFoundException extends FrameworkExceptions{
    /**
     * PropertyNotFoundException with only message
     * @param message: error/stack trace message
     */
    public PropertyNotFoundException(String message){
        super(message);
    }

    /**
     * PropertyNotFoundException with custom message and throwable
     * @param message: error/stack trace message
     * @param cause: can be some throwable
     */
    public PropertyNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
