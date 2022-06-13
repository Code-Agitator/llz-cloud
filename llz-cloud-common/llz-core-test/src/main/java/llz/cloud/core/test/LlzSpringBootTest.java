package llz.cloud.core.test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;

import javax.swing.*;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootTest
@ExtendWith(LlzSpringExtension.class)
public @interface LlzSpringBootTest {
    /**
     * 是否加载SPI
     */
    boolean enableLoader() default true;

    String appName() default "";

    String profile() default "dev";

    @AliasFor(annotation = SpringBootTest.class)
    Class<?>[] classes() default {};

}
