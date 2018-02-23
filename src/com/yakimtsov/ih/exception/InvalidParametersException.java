package com.yakimtsov.ih.exception;

/**
 * Created by Ivan on 20.01.2018.
 */
public class InvalidParametersException extends Exception {
    public InvalidParametersException(String message) {
        super(message);
    }

    public InvalidParametersException() {
    }

    public InvalidParametersException(Throwable th){
        super(th);
    }

    public InvalidParametersException(String m,Throwable th){
        super(m,th);
    }
}
