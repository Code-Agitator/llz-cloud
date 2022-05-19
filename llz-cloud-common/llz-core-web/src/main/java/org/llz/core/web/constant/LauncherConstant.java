package org.llz.core.web.constant;

import java.util.HashMap;
import java.util.Map;

public class LauncherConstant {
    private LauncherConstant() {
    }

    private static final Map<String, String> NACOS_ADDR_MAP;

    static {
        NACOS_ADDR_MAP = new HashMap<>(3);
        NACOS_ADDR_MAP.put("dev", "127.0.0.1:8848");
    }

    public static String nacosAddr(String profile) {
        return NACOS_ADDR_MAP.get(profile);
    }
}
