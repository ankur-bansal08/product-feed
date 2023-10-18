package com.tradedoubler.productfeed.Exception;

import java.io.Serial;


public class ProductNotFoundException extends ProductFeedException {

    @Serial
    private static final long serialVersionUID = 1L;


    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public ProductNotFoundException(Throwable exception) {
        super(exception);
    }

    public ProductNotFoundException(String message, Throwable exception) {
        super(message, exception);
    }

    public ProductNotFoundException(String message, String errorCode, Throwable exception) {
        super(message, errorCode, exception);
    }
}
