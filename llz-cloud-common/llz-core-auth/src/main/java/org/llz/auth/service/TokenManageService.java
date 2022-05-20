package org.llz.auth.service;

public interface TokenManageService {
    /**
     * @param subject              token携带参数
     * @param expiredInMillisecond 过期时间
     * @return token
     */
    String createToken(String subject, Long expiredInMillisecond);
}