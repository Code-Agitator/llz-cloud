package org.llz.sentinel.handler;

import lombok.extern.slf4j.Slf4j;
import org.llz.common.entity.result.Result;
import org.llz.sentinel.exception.FeignSentinelException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class DefaultFallbackInvocationHandler implements InvocationHandler {
    private Class<?> type;

    public DefaultFallbackInvocationHandler(Class<?> type) {
        this.type = type;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.error("接口{}方法{}请求失败, 触发熔断降级处理", type.getName(), method.getName());
        Class<?> returnType = method.getReturnType();
        // 熔断后响应结果 (留坑
        if (Result.class.isAssignableFrom(returnType)) {

        }
        throw new FeignSentinelException(String.format("接口%s方法%s请求失败, 触发熔断降级处理", type.getName(), method.getName()));
    }

}
