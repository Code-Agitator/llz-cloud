package org.llz.common.constant;

public enum ResultConst {
    SUCCESS("200", "ok"),
    NOT_FOUND("404", "404 NOT FOUND"),
    UNAUTHORIZED_NO_TOKEN("4010", "token is blank,and require not blank"),
    UNAUTHORIZED_INVALID("4011", "token is not legal"),
    UNAUTHORIZED_EXPIRED("4012", "token has been expire");

    String code;
    String message;


    ResultConst(String code, String message) {
        this.code = code;
        this.message = message;
    }



    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
