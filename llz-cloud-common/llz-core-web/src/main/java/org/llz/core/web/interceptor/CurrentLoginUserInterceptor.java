package org.llz.core.web.interceptor;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.llz.annotation.web.CurrentLoginUser;
import org.llz.auth.constant.AuthConstant;
import org.llz.auth.service.TokenManageService;
import org.llz.common.exception.TokenException;
import org.llz.common.util.InterceptorUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 当前登录用户拦截器
 */
@Component
@Slf4j
@AllArgsConstructor
public class CurrentLoginUserInterceptor implements HandlerInterceptor {
    private final TokenManageService tokenManageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<CurrentLoginUser> parameterAnnotation = InterceptorUtil.getParameterAnnotation(handler, CurrentLoginUser.class);
        if (ArrayUtil.isEmpty(parameterAnnotation)) {
            // 没有定义 不拦截
            return true;
        }
        if (parameterAnnotation.size() > 1) {
            // 不要定义大于一个的 当前登录用户信息注解 铁汁
            log.error("[@CurrentLoginUser]please do not define more then 1 annotation of @CurrentLoginUser");
            return false;
        }
        // 获取当前用户注解
        CurrentLoginUser currentLoginUser = parameterAnnotation.get(0);
        // 获取是否必须含有用户信息配置
        boolean require = currentLoginUser.require();
        // 从请求头获取token
        String token = getToken(request);
        boolean tokenIsBlank = CharSequenceUtil.isBlank(token);
        if (require && tokenIsBlank) {
            log.info("[@CurrentLoginUser]current request require a login user,but token is blank,interceptor make it over");
            return false;
        }
        // token不为空 解析token
        if (!tokenIsBlank) {
            try {
                tokenManageService.getSubject(token);
            } catch (TokenException e) {
                log.warn("[@CurrentLoginUser]token parse fail, cause:", e);
                return !require;
            }
        }

        return true;
    }

    private String getToken(HttpServletRequest request) {
        return request.getHeader(AuthConstant.TOKEN_NAME_IN_HEADER);
    }

}
