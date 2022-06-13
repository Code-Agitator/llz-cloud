package org.llz.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import org.llz.common.exception.HttpRequestException;

import java.util.Collections;
import java.util.List;

public class HttpUtil {

    private static final int DEFAULT_TIMEOUT = 20000;

    public static HttpResponse doPost(String url, List<Pair<String, Object>> params, List<Pair<String, String>> headers, int timeout) {
        HttpRequest request = HttpRequest.post(url);

        if (CollUtil.isNotEmpty(params)) {
            params.forEach(param -> request.form(param.getKey(), param.getValue()));
        }

        if (CollUtil.isNotEmpty(headers)) {
            headers.forEach(header -> request.header(header.getKey(), header.getValue()));
        }

        request.timeout(timeout);

        return request.execute();
    }

    public static HttpResponse doPost(String url, String json, List<Pair<String, String>> headers, int timeout) throws HttpRequestException {
        HttpRequest request = HttpRequest.post(url);

        if (CharSequenceUtil.isNotBlank(json)) {
            request.body(json);
        }

        if (CollUtil.isNotEmpty(headers)) {
            headers.forEach(header -> request.header(header.getKey(), header.getValue()));
        }

        request.timeout(timeout);
        HttpResponse response = request.execute();
        if (response.getStatus() != HttpStatus.HTTP_OK) {
            throw new HttpRequestException("请求失败", response);
        }
        return response;
    }


    public static HttpResponse doPost(String url, String json, List<Pair<String, String>> headers) {
        return doPost(url, json, headers, DEFAULT_TIMEOUT);
    }

    public static HttpResponse doPost(String url, String json) {
        return doPost(url, json, Collections.emptyList());
    }


    public static HttpResponse doPost(String url, List<Pair<String, Object>> params, List<Pair<String, String>> headers) {
        return doPost(url, params, headers, DEFAULT_TIMEOUT);
    }

    public static HttpResponse doPost(String url, List<Pair<String, Object>> params) {
        return doPost(url, params, Collections.emptyList());
    }

    public static HttpResponse doPost(String url) {
        return doPost(url, Collections.emptyList());
    }


}
