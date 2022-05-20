package org.llz.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("token.manage.jwt")
@Data
public class JwtTokenManageProperties {
    private long oneDayMillisSecond = (long) 1000 * 60 * 60 * 24;
    /**
     * issuer 用户
     */
    private String issuer = "llz";

    /**
     * 秘钥
     */
    private String secretKey;
    /**
     * 默认的 accessToken 的有效毫秒数
     */
    private long defaultAccessTokenMillisSecond = oneDayMillisSecond;
    /**
     * 默认的 refreshToken 的有效毫秒数
     */
    private long defaultRefreshTokenMillisSecond = oneDayMillisSecond * 3;

}
