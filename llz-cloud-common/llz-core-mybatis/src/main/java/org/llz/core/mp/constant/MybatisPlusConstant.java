package org.llz.core.mp.constant;

import java.util.HashMap;
import java.util.Map;

public class MybatisPlusConstant {
    private MybatisPlusConstant(){

    }
    private static final boolean LOG_DEV_ENABLE = true;
    private static final Map<String, Boolean> LOG_MAP = new HashMap<>(3);

    static {
        LOG_MAP.put("dev", LOG_DEV_ENABLE);
    }


    public static boolean logEnable(String profile) {
        return LOG_MAP.get(profile);
    }
}
