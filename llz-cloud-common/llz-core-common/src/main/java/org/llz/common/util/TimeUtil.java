package org.llz.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeUtil {
    private TimeUtil() {
    }

    public static final ZoneId CHINESE_ZONE_ID = ZoneId.of("Asia/Shanghai");

    /**
     * 获取当前中国时区的时间 LocalDateTime 类型
     */
    public static LocalDateTime nowLocalDateTime() {
        return LocalDateTime.now(CHINESE_ZONE_ID);
    }

    /**
     * 获取当前中国时区的时间
     */
    public static Date now() {
        return Date.from(nowLocalDateTime().atZone(CHINESE_ZONE_ID).toInstant());
    }

}
