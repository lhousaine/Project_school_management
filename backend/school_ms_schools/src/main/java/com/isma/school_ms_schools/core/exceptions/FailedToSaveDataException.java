package com.isma.school_ms_schools.core.exceptions;

public class FailedToSaveDataException extends Exception {
    /***
     *
     * @param message
     * @param cause
     */
    public FailedToSaveDataException(String message, Throwable cause) {
        super(message, cause);
    }
    /***
     *
     * @param message
     */
    public FailedToSaveDataException(String message) {
        super(message);
    }
}
