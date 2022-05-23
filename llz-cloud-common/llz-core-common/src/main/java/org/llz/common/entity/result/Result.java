package org.llz.common.entity.result;

import lombok.Data;
import org.llz.common.constant.ResultConst;

@Data
public class Result {
    /**
     * 结果code, 参考常量 ResultConst.Code
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    public Result() {
        this.code = ResultConst.Code.SUCCESS;
        this.message = "ok";
    }


    public static Result success() {
        return new Result();
    }
}
