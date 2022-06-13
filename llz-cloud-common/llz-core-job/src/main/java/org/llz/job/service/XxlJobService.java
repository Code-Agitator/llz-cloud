package org.llz.job.service;

import cn.hutool.core.lang.Pair;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.llz.common.exception.HttpRequestException;
import org.llz.common.util.HttpUtil;
import org.llz.job.entity.XxlJobInfo;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.llz.job.constant.XxlJobApiConstant.ADD_URL;
import static org.llz.job.constant.XxlJobApiConstant.LOGIN_URL;


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
        // 查询对应groupId:
        jobInfo.setJobGroup(1);
        String json2 = JSONUtil.toJsonStr(jobInfo);
        HttpResponse res = null;
        try {
            res = HttpUtil.doPost(adminAddresses + ADD_URL, json2);
        } catch (HttpRequestException requestException) {
            log.warn("添加任务失败:响应体:{}", requestException.getResponse());
        }
        return res.body();
    }

    private String getLoginCookie() {
        HttpUtil.doPost(adminAddresses + LOGIN_URL, Stream.of(
                new Pair<String, Object>("username", username),
                new Pair<String, Object>("password", password)
        ).collect(Collectors.toList()));
        return "";
    }


}
