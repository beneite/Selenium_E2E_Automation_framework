package com.asismisr.exceptions;

public class BrowserNotFoundException extends FrameworkExceptions{

    /**
     * BrowserNotFoundException with only message
     * @param message: error/stack trace message
     */
    public BrowserNotFoundException(String message){
        super(message);
    }

    /**
     * BrowserNotFoundException with custom message and throwable
     * @param message: error/stack trace message
     * @param cause: can be some throwable
     */
    public BrowserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }


}
