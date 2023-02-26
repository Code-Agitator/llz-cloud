package org.llz.core.web.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ResultInfo<T> extends Result {
    /**
     * 携带数据
     */
    private T data;


    public ResultInfo(T data) {
        super();
        this.data = data;
    }

    public static <A> ResultInfo<A> success(A data) {
        return new ResultInfo<>(data);
    }
}
