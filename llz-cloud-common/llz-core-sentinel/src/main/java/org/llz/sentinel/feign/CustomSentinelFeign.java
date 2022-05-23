package org.llz.sentinel.feign;

import feign.Feign;
import org.llz.sentinel.handler.DefaultFallbackInvocationHandler;
import org.llz.sentinel.handler.SentinelInvocationHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClientFactoryBean;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StringUtils;

public class CustomSentinelFeign {

    private CustomSentinelFeign() {
    }

    public static Feign.Builder builder() {
        return new Builder();
    }

    public static class Builder extends Feign.Builder implements ApplicationContextAware {
        /**
         * 用于获取 FeignContext
         */
        private ApplicationContext applicationContext;

        private FeignContext feignContext;

        public Builder() {
            super();
        }


        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
            feignContext = this.applicationContext.getBean(FeignContext.class);
        }


        @Override
        public Feign build() {
            super.invocationHandlerFactory((target, methodHandlerMap) -> {
                GenericApplicationContext gctx = (GenericApplicationContext) Builder.this.applicationContext;
                BeanDefinition definition = gctx.getBeanDefinition(target.type().getName());
                // 得到Feign Client Bean
                FeignClientFactoryBean feignClientFactoryBean = (FeignClientFactoryBean) definition
                        .getAttribute("feignClientsRegistrarFactoryBean");

                assert feignClientFactoryBean != null;
                // 得到fallback
                Class<?> fallback = feignClientFactoryBean.getFallback();
                // 得到fallbackFactory
                Class<?> fallbackFactory = feignClientFactoryBean.getFallbackFactory();
                // 获取bean name
                String beanName = feignClientFactoryBean.getContextId();
                if (!StringUtils.hasText(beanName)) {
                    // 获取服务名称
                    beanName = feignClientFactoryBean.getName();
                }
                // fallback实例
                Object fallbackInstance;
                // fallbackFactory实例
                FallbackFactory<?> fallbackFactoryInstance;
                // 检查 fallback and fallbackFactory properties
                // 如果他是一个fallback类
                if (void.class != fallback) {
                    fallbackInstance = getFromContext(beanName, "fallback", fallback, target.type());
                    return new SentinelInvocationHandler(target, methodHandlerMap,
                            new FallbackFactory.Default<>(fallbackInstance));
                }
                // 如果他又fallbackFactory
                if (void.class != fallbackFactory) {
                    fallbackFactoryInstance = (FallbackFactory<?>) getFromContext(beanName, "fallbackFactory",
                            fallbackFactory, FallbackFactory.class);
                    return new SentinelInvocationHandler(target, methodHandlerMap, fallbackFactoryInstance);
                }

                return new SentinelInvocationHandler(target, methodHandlerMap, new FallbackFactory.Default<>(
                        new DefaultFallbackInvocationHandler(target.type())));
            });
            return super.build();
        }

        /**
         * 从feignContext中获取fallback实例
         */
        private Object getFromContext(String name, String type, Class<?> fallbackType, Class<?> targetType) {
            // 直接获取
            Object fallbackInstance = feignContext.getInstance(name, fallbackType);
            if (fallbackInstance == null) {
                throw new IllegalStateException(String.format(
                        "No %s instance of type %s found for feign client %s", type, fallbackType, name));
            }

            if (!targetType.isAssignableFrom(fallbackType)) {
                throw new IllegalStateException(String.format(
                        "Incompatible %s instance. Fallback/fallbackFactory of type %s is not assignable to %s for feign client %s",
                        type, fallbackType, targetType, name));
            }
            return fallbackInstance;
        }
    }
}
