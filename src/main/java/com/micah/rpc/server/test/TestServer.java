package com.micah.rpc.server.test;

import com.micah.rpc.server.controller.NettyRpcServer;
import com.micah.rpc.server.controller.RpcServer;
import com.micah.rpc.server.provider.ServiceProvider;
import com.micah.rpc.service.impl.BlogServiceImpl;
import com.micah.rpc.service.impl.UserServiceImpl;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午1:40
 * @Version 1
 * @Description
 */
public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInstance(userService);
        serviceProvider.provideServiceInstance(blogService);

        RpcServer rpcServer = new NettyRpcServer(serviceProvider);
        rpcServer.start(8787);
    }
}
