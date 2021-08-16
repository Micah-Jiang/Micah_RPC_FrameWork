package com.micah.rpc.server.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午1:26
 * @Version 1
 * @Description 服务提供类 将之前我们自己手写的服务名换成class反射自动获取到
 */
public class ServiceProvider {
    /**
     * 一个类可能实现多个接口
     */
    private final Map<String, Object> serviceMap;

    public ServiceProvider() {
        this.serviceMap = new HashMap<>();
    }

    /**
     * 根据服务类名，来统计其实现的接口
     * @param service 服务实现类
     */
    public void provideServiceInstance(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces();

        for (Class<?> clazz : interfaces) {
            serviceMap.put(clazz.getName(), service);
        }
    }

    public Object getService(String interfaceName){
        return serviceMap.get(interfaceName);
    }
}
