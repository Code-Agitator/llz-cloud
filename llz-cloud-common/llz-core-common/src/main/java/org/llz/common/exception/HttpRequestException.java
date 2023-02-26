package org.llz.common.exception;

import cn.hutool.http.HttpResponse;

/**
 * 内部网络请求异常
 */
public class HttpRequestException extends RuntimeException {
    private final HttpResponse response;

    public HttpRequestException(String message, HttpResponse response) {
        super(message);
        this.response = response;
    }

    public HttpResponse getResponse() {
        return response;
    }
}
