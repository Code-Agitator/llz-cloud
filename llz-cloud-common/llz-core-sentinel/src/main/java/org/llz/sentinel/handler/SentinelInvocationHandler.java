package org.llz.sentinel.handler;

import feign.InvocationHandlerFactory;
import feign.Target;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class SentinelInvocationHandler implements InvocationHandler {
    private final Target<?> target;

    private final Map<Method, InvocationHandlerFactory.MethodHandler> methodHandlerMap;

    private FallbackFactory<?> fallbackFactory;

    private Map<Method, Method> fallbackMethodMap;

    public SentinelInvocationHandler(Target<?> target, Map<Method, InvocationHandlerFactory.MethodHandler> methodHandlerMap, FallbackFactory<?> fallbackFactory) {
        this.target = target;
        this.methodHandlerMap = methodHandlerMap;
        this.fallbackFactory = fallbackFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
