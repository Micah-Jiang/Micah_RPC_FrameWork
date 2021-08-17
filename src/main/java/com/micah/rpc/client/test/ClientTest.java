package com.micah.rpc.client.test;

import com.micah.rpc.client.ClientProxy;
import com.micah.rpc.client.NettyRpcClient;
import com.micah.rpc.client.RpcClient;
import com.micah.rpc.common.enity.User;
import com.micah.rpc.service.UserService;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午2:46
 * @Version 1
 * @Description
 */
public class ClientTest {
    public static void main(String[] args) {
        // 构建一个使用java Socket传输的客户端
        RpcClient rpcClient = new NettyRpcClient();
        // 把这个客户端传入代理客户端
        ClientProxy clientProxy = new ClientProxy(rpcClient);
        // 代理客户端根据不同的服务，获得一个代理类， 并且这个代理类的方法以或者增强（封装数据，发送请求）
        UserService userService = clientProxy.getProxy(UserService.class);
        // 调用方法
        User user = userService.getUserById(10);
        System.out.println("select user by id " + user);
    }
}
