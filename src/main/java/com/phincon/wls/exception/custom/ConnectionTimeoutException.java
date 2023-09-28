package com.phincon.wls.exception.custom;

public class ConnectionTimeoutException extends RuntimeException {
    public ConnectionTimeoutException(String message) {
        super(message);
    }
}
