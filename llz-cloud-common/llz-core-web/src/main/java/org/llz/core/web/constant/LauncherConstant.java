package org.llz.core.web.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LauncherConstant {
    private LauncherConstant() {
    }

    private static final Map<String, String> NACOS_ADDR_MAP;
    private static final String[] SHARE_CONFIG_FILE = new String[]{"share.yml"};

    static {
        NACOS_ADDR_MAP = new HashMap<>(3);
        NACOS_ADDR_MAP.put("dev", "192.168.113.208:8848");
    }

    public static String nacosAddr(String profile) {
        return NACOS_ADDR_MAP.get(profile);
    }

    public static String[] getShareConfigFile() {
        return SHARE_CONFIG_FILE;
    }
}