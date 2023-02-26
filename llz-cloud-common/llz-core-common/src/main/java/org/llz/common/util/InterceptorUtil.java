package org.llz.common.util;

import cn.hutool.core.util.ArrayUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterceptorUtil {
    private InterceptorUtil() {
    }

    /**
     * 获得方法参数注解
     *
     * @param handler HandlerMethod 处理方法
     * @param clazz   注解类型对象
     * @param <T>     注解类型
     * @return 注解列表列表
     */
    public static <T extends Annotation> List<T> getParameterAnnotation(Object handler, Class<T> clazz) {
        if (handler instanceof HandlerMethod) {
            // 获取方法
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 方法参数
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            if (ArrayUtil.isNotEmpty(methodParameters)) {
                List<T> list = new ArrayList<>();
                for (MethodParameter methodParameter : methodParameters) {
                    T parameterAnnotation = methodParameter.getParameterAnnotation(clazz);
                    if (parameterAnnotation != null) {
                        list.add(parameterAnnotation);
                    }
                }
                return list;
            }

        }
        return Collections.emptyList();
    }


    /**
     * 获得方法参数注解
     *
     * @param handler HandlerMethod 处理方法
     * @param clazz   注解类型对象
     * @param <T>     注解类型
     * @return 注解列表列表
     */
    public static <T extends Annotation> boolean hasParameterAnnotation(Object handler, Class<T> clazz) {
        if (handler instanceof HandlerMethod) {
            // 获取方法
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 方法参数
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            if (ArrayUtil.isNotEmpty(methodParameters)) {
                for (MethodParameter methodParameter : methodParameters) {
                    T parameterAnnotation = methodParameter.getParameterAnnotation(clazz);
                    if (parameterAnnotation != null) {
                        return true;
                    }
                }
            }

        }
        return false;
    }
}

