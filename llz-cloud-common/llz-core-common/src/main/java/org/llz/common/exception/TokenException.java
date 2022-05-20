package org.llz.common.exception;

public class TokenException extends RuntimeException {
    public TokenException(String code, Exception message) {
        super(code);
    }
}
