package org.llz.common.constant;

public enum ResultConst {
    SUCCESS("200", "ok");

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
