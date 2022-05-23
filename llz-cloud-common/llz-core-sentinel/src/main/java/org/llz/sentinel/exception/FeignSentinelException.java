package org.llz.sentinel.exception;

public class FeignSentinelException extends RuntimeException {
    public FeignSentinelException(String message) {
        super(message);
    }
}
