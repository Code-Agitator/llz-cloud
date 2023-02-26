package org.llz.core.web.entity;

import lombok.Data;
import org.llz.common.constant.ResultConst;

@Data
public class Result {
    /**
     * 结果code
     *
     * @see ResultConst
     */
    private String code;

    /**
     * 提示信息
     *
     * @see ResultConst
     */
    private String message;


    public Result() {
        this.code = ResultConst.SUCCESS.code();
        this.message = ResultConst.SUCCESS.message();
    }

    public Result(ResultConst resultConst) {
        this.code = resultConst.code();
        this.message = resultConst.message();
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String message) {
        this.code = ResultConst.SUCCESS.code();
        this.message = message;
    }


    public static Result success() {
        return new Result();
    }

    public static Result success(String message) {
        return new Result(message);
    }

    public static Result fail(ResultConst resultConst) {
        return new Result(resultConst);
    }

    public static Result fail(int code, String message) {
        return fail(code + "", message);
    }

    public static Result fail(String code, String message) {
        return new Result(code, message);
    }
}
