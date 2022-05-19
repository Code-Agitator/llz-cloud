package org.llz.annotation.base;

import java.lang.annotation.*;

/**
 * 放在对应的实现类上
 * 用于生成 spi 对应的文件信息
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Documented
public @interface SPIAuto {

    /**
     * 目标文件夹
     */
    String dir() default "META-INF/services/";

    /**
     * 对应的 spi 注解信息
     */
    Class<? extends Annotation> spi() default SPI.class;

}