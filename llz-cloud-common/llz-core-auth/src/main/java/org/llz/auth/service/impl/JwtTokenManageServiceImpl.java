package org.llz.auth.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import org.llz.auth.properties.JwtTokenManageProperties;
import org.llz.auth.service.TokenManageService;
import org.llz.common.exception.TokenException;
import org.llz.common.util.TimeUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class JwtTokenManageServiceImpl implements TokenManageService {
    private JwtTokenManageProperties jwtTokenManageProperties;

    public JwtTokenManageServiceImpl(JwtTokenManageProperties jwtTokenManageProperties) {
        Objects.requireNonNull(jwtTokenManageProperties, "缺少Jwt相关的配置");
        this.jwtTokenManageProperties = jwtTokenManageProperties;
    }


    @Override
    public String createToken(String subject, Long expiredInMillisecond) {
        try {
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.issuer(jwtTokenManageProperties.getIssuer());
            builder.subject(subject);
            builder.issueTime(TimeUtil.now());
            builder.notBeforeTime(TimeUtil.now());
            // 设置过期时间
            if (!Objects.equals(expiredInMillisecond, -1L)) {
                builder.expirationTime(new Date(TimeUtil.now().getTime() + expiredInMillisecond));
            }
            builder.jwtID(UUID.randomUUID().toString());
            JWTClaimsSet claimsSet = builder.build();
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            Payload payload = new Payload(claimsSet.toJSONObject());
            JWSObject jwsObject = new JWSObject(header, payload);
            JWSSigner signer = new MACSigner(getSecret());
            jwsObject.sign(signer);
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("生成token出现异常,{}", e.getMessage());
            throw new TokenException("生成Token出现了异常", e);
        }

    }

    private String getSecret() {
        Objects.requireNonNull(jwtTokenManageProperties.getSecretKey(), "请配置jwt相关密钥");
        return jwtTokenManageProperties.getSecretKey();
    }
}
