package org.llz.common.constant;

public enum ResultConst {
    SUCCESS("200", "ok"),
    UNAUTHORIZED_NO_TOKEN("4010", "token不能为空"),
    UNAUTHORIZED_INVALID("4011", "token不合法"),
    UNAUTHORIZED_EXPIRED("4012", "token已超时过期");

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
