package com.isma.school_ms_payment.core.exceptions;

public class AmountSurplusException extends Exception{
    public AmountSurplusException() {
    }

    public AmountSurplusException(String message) {
        super(message);
    }

    public AmountSurplusException(String message, Throwable cause) {
        super(message, cause);
    }
}
