package com.micah.rpc.server.controller.impl;

import com.micah.rpc.server.controller.RpcServer;
import com.micah.rpc.server.provider.ServiceProvider;
import com.micah.rpc.server.work.WorkThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午12:53
 * @Version 1
 * @Description
 */
public class ThreadPoolRpcServer implements RpcServer {
    /**
     * 线程池
     */
    private final ThreadPoolExecutor threadPool;
    /**
     * 存储所有服务的Map
     */
    private final ServiceProvider serviceProvide;

    public ThreadPoolRpcServer(ServiceProvider serviceProvide){
        threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1000, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());
        this.serviceProvide = serviceProvide;
    }

    public ThreadPoolRpcServer(ServiceProvider serviceProvider, int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue){

        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadPoolExecutor.CallerRunsPolicy());
        this.serviceProvide = serviceProvider;
    }
    @Override
    public void start(int port) {
        System.out.println("服务端启动了");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                threadPool.execute(new WorkThread(socket,serviceProvide));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
    }
}

