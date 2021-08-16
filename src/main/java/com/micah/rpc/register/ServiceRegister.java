package com.micah.rpc.register;

import java.net.InetSocketAddress;

/**
 * @Author m.kong
 * @Date 2021/8/16 下午4:07
 * @Version 1
 * @Description 服务注册接口
 */
public interface ServiceRegister {
    /**
     * 保存服务地址和名称
     * @param serviceName 服务名称
     * @param serverAddress 服务地址
     */
    void register(String serviceName, InetSocketAddress serverAddress);

    /**
     * 根据服务名获取服务地址
     * @param serviceName 服务名称
     * @return 服务地址
     */
    InetSocketAddress serviceDiscovery(String serviceName);
}
