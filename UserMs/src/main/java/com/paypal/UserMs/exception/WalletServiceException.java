package com.paypal.UserMs.exception;

public class WalletServiceException extends RuntimeException{
    public WalletServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
