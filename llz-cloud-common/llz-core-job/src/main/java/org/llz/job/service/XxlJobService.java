package org.llz.job.service;

import cn.hutool.core.lang.Pair;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.llz.common.exception.HttpRequestException;
import org.llz.common.util.HttpUtil;
import org.llz.job.entity.XxlJobInfo;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.llz.job.constant.XxlJobApiConstant.ADD_URL;
import static org.llz.job.constant.XxlJobApiConstant.LOGIN_URL;
import static org.llz.job.constant.XxlJobConstant.XXL_LOGIN_COOKIE_KEY;


@Slf4j
public class XxlJobService {
    private final String adminAddresses;

    private final String appname;


    private final String username;

    private final String password;


    private ReentrantLock loginCookieLock;

    public XxlJobService(String adminAddresses, String appname, String username, String password) {
        this.adminAddresses = adminAddresses;
        this.appname = appname;
        this.username = username;
        this.password = password;
    }

    public String add(XxlJobInfo jobInfo) {
        try {
            // 查询对应groupId:
            jobInfo.setJobGroup(1);
            final List<Pair<String, Object>> param = Arrays.stream(jobInfo.getClass().getDeclaredFields())
                    .filter(field -> !field.getName().equals("serialVersionUID"))
                    .map(field -> {
                        try {
                            String name = field.getName();

                            name = name.substring(0, 1).toUpperCase() + name.substring(1);
                            Object value = jobInfo.getClass().getMethod("get" + name).invoke(jobInfo);
                            return new Pair<>(name, value);
                        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).collect(Collectors.toList());
            HttpResponse res = null;
            final String loginCookie = getLoginCookie();
            res = HttpUtil.doPost(adminAddresses + ADD_URL, param, Stream.of(
                    new Pair<>("cookie", XXL_LOGIN_COOKIE_KEY + "=" + loginCookie)
            ).collect(Collectors.toList()));
            return res.body();
        } catch (HttpRequestException requestException) {
            log.warn("添加任务失败:响应体:{}", requestException.getResponse());
        }
        return null;
    }

    private String getLoginCookie() {
        final HttpResponse httpResponse = HttpUtil.doPost(adminAddresses + LOGIN_URL, Stream.of(
                new Pair<String, Object>("userName", username),
                new Pair<String, Object>("password", password)
        ).collect(Collectors.toList()));
        return httpResponse.getCookieValue(XXL_LOGIN_COOKIE_KEY);
    }


}
