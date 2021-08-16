package com.micah.rpc.server.controller;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午12:31
 * @Version 1
 * @Description 服务端写出接口，后续服务类直接实现本接口即可
 */
public interface RpcServer {
    /**
     * 启动服务端
     * @param port 端口
     */
    void start(int port);

    /**
     * 停止服务
     */
    void stop();
}
