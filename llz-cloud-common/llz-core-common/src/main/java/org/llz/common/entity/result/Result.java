package org.llz.common.entity.result;

import lombok.Data;
import org.llz.common.constant.ResultConst;

@Data
public class Result<T> {
    /**
     * 结果code, 参考常量 ResultConst.Code
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 携带数据
     */
    private T data;

    public Result() {
        this.code = ResultConst.SUCCESS.code();
        this.message = ResultConst.SUCCESS.message();
    }

    public Result(T data) {
        this.code = ResultConst.SUCCESS.code();
        this.message = ResultConst.SUCCESS.message();
        this.data = data;
    }


    public static Result<?> success() {
        return new Result<>();
    }

    public static <F> Result<F> success(F data) {
        return new Result<>(data);
    }


}
