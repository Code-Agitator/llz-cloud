package org.llz.common.constant;

public enum ResultConst {
    SUCCESS("200", "ok"),
    UNAUTHORIZED_NO_TOKEN("4010", "token不能为空");

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
