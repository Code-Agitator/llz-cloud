package org.llz.common.exception;

import cn.hutool.http.HttpResponse;

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
