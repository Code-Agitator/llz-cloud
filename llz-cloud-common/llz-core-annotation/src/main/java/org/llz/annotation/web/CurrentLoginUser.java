package org.llz.annotation.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 当前登录用户注解
 */
@Target(ElementType.PARAMETER)
@Retention(RUNTIME)
public @interface CurrentLoginUser {
    /**
     * 是否必须含有用户信息
     */
    boolean require() default true;
}
