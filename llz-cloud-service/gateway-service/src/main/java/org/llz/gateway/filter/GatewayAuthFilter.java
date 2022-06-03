package org.llz.gateway.filter;


import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.llz.auth.service.TokenManageService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class GatewayAuthFilter implements GlobalFilter, Ordered {
    TokenManageService tokenManageService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final String token = exchange.getRequest().getHeaders().getFirst("token");
        if (StrUtil.isNotBlank(token)) {
            final String subject = tokenManageService.getSubject(token, true);
            log.info("subject {}", subject);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
