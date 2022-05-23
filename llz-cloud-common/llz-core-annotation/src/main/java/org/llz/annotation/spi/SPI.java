package org.llz.annotation.spi;

import java.lang.annotation.*;

/**
 * 放在接口类上
 * SPI 注解定义
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface SPI {
}
