package org.llz.job.service;

import cn.hutool.core.lang.Pair;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.llz.common.util.HttpUtil;
import org.llz.job.entity.XxlJobInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.llz.job.constant.XxlJobApiConstant.ADD_URL;
import static org.llz.job.constant.XxlJobApiConstant.GET_GROUP_ID;

public class XxlJobService {
    private String adminAddresses;

    private String appname;

    public XxlJobService(String adminAddresses, String appname) {
        this.adminAddresses = adminAddresses;
        this.appname = appname;
    }

    public String add(XxlJobInfo jobInfo) {
        // 查询对应groupId:
        String result = HttpUtil.doPost(adminAddresses + GET_GROUP_ID,
                Stream.of(
                        new Pair<String, Object>("appname", appname)
                ).collect(Collectors.toList()));
        JSONObject json = (JSONObject) JSONUtil.parse(result);

        String groupId = json.getStr("content");
        jobInfo.setJobGroup(Integer.parseInt(groupId));
        String json2 = JSONUtil.toJsonStr(jobInfo);
        return HttpUtil.doPost(adminAddresses + ADD_URL, json2);
    }
}
