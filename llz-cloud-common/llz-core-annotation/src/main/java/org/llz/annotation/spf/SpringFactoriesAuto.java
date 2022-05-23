package org.llz.annotation.spf;

import java.lang.annotation.*;

/**
 * 自动生成 spring.factories文件
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Documented
public @interface SpringFactoriesAuto {
    /**
     * 目标文件夹
     */
    String dir() default "META-INF/";
}
