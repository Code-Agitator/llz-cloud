package org.llz.common.exception;

import org.llz.common.constant.ResultConst;

/**
 * token异常
 */
public class TokenException extends RuntimeException {
    public TokenException(ResultConst result, String message) {
        super(message);
    }

    public TokenException(String result, String message) {
        super(message);
    }


}
