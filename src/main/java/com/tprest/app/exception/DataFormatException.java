package com.tprest.app.exception;

/**
 * Created by marwen on 23/03/16.
 */
public final class DataFormatException extends RuntimeException {

    public DataFormatException() {
        super();
    }

    public DataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFormatException(String message) {
        super(message);
    }

    public DataFormatException(Throwable cause) {
        super(cause);
    }
}
