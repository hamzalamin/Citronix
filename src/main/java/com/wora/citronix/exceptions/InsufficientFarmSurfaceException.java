package com.wora.citronix.exceptions;

public class InsufficientFarmSurfaceException extends RuntimeException {
    public InsufficientFarmSurfaceException(String message) {
        super(message);
    }
    public InsufficientFarmSurfaceException(String message, Throwable cause) {
        super(message, cause);
    }
}
