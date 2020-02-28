package com.isma.school_ms_payment.core.exceptions;

public class DataAlreadyUsed extends Exception{
    public DataAlreadyUsed() {
    }

    public DataAlreadyUsed(String message) {
        super(message);
    }

    public DataAlreadyUsed(String message, Throwable cause) {
        super(message, cause);
    }
}
