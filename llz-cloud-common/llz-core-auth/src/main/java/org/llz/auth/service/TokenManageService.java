package org.llz.auth.service;

public interface TokenManageService {
    /**
     * @param subject              token携带参数
     * @param expiredInMillisecond 过期时间
     * @return token
     */
    String createToken(String subject, Long expiredInMillisecond);

    /**
     * 获得token携带参数
     *
     * @param token          token
     * @param ifTokenExpired 是否过期
     * @return token携带参数
     */
    String getSubject(String token, boolean ifTokenExpired);

    /**
     * 检查token是否合法
     *
     * @param token token
     * @return (boolean)
     */
    boolean isTokenLegal(String token);

}