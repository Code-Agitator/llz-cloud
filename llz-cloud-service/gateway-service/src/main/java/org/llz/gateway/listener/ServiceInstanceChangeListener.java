package org.llz.gateway.listener;

import com.alibaba.nacos.client.naming.event.InstancesChangeEvent;
import com.alibaba.nacos.common.notify.Event;
import com.alibaba.nacos.common.notify.listener.Subscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cache.Cache;
import org.springframework.cloud.loadbalancer.cache.LoadBalancerCacheManager;
import org.springframework.cloud.loadbalancer.core.CachingServiceInstanceListSupplier;
import org.springframework.context.ApplicationContext;

/**
 * 监听服务实例变化 更新LoadBalancerCacheManager 缓存
 */
@Slf4j
public class ServiceInstanceChangeListener extends Subscriber<InstancesChangeEvent> {

    ApplicationContext applicationContext;

    public ServiceInstanceChangeListener(ApplicationContext applicationContext) {
        super();
        this.applicationContext = applicationContext;
    }

    @Override
    public void onEvent(InstancesChangeEvent instancesChangeEvent) {
        ObjectProvider<LoadBalancerCacheManager> beanProvider = applicationContext.getBeanProvider(LoadBalancerCacheManager.class);
        LoadBalancerCacheManager cacheManager = beanProvider.getIfAvailable();
        if (cacheManager == null) {
            return;
        }
        String serviceName = instancesChangeEvent.getServiceName();
        Cache cache = cacheManager.getCache(CachingServiceInstanceListSupplier.SERVICE_INSTANCE_CACHE_NAME);
        if (cache != null) {
            cache.evict(serviceName);
            log.info("服务[{}]的实例数量发生变更, 清空缓存重新获取最新的实例列表", serviceName);
        }

    }

    @Override
    public Class<? extends Event> subscribeType() {
        return InstancesChangeEvent.class;
    }
}
