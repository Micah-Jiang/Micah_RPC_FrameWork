package com.micah.rpc.server.provider;

import com.micah.rpc.register.ServiceRegister;
import com.micah.rpc.register.impl.ZkServiceRegister;

import java.net.InetSocketAddress;
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
    private final ServiceRegister serviceRegister;
    private final String host;
    private final int port;

    public ServiceProvider(String host, int port) {
        this.host = host;
        this.port = port;
        this.serviceMap = new HashMap<>();
        this.serviceRegister = new ZkServiceRegister();
    }

    /**
     * 根据服务类名，来统计其实现的接口
     * @param service 服务实现类
     */
    public void provideServiceInstance(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces();

        for (Class<?> clazz : interfaces) {
            //本机服务映射Map
            serviceMap.put(clazz.getName(), service);
            //new code, update in 2021/8/17 --- 注册服务
            serviceRegister.register(clazz.getName(),new InetSocketAddress(host, port));
        }
    }

    public Object getService(String interfaceName){
        return serviceMap.get(interfaceName);
    }
}
