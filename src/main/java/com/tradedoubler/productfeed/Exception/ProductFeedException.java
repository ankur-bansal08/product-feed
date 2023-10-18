package com.tradedoubler.productfeed.Exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ProductFeedException extends Exception {

    @Serial
    private static final long serialVersionUID = 2206436657505193108L;
    private final String errorCode;
    private final Throwable linkedException;

    public ProductFeedException(String message) {
        this(message, (String)null, (Throwable)null);
    }

    public ProductFeedException(String message, String errorCode) {
        this(message, errorCode, (Throwable)null);
    }

    public ProductFeedException(Throwable exception) {
        this((String)null, (String)null, exception);
    }

    public ProductFeedException(String message, Throwable exception) {
        this(message, (String)null, exception);
    }

    public ProductFeedException(String message, String errorCode, Throwable exception) {
        super(message);
        this.errorCode = errorCode;
        this.linkedException = exception;
    }
}
