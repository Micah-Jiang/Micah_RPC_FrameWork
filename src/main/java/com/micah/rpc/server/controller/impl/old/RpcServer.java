package com.micah.rpc.server.controller.impl.old;

import com.micah.rpc.common.msg.RpcRequest;
import com.micah.rpc.common.msg.RpcResponse;
import com.micah.rpc.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author m.kong
 * @Date 2021/8/11 下午2:25
 * @Version 1
 * @Description 服务端 以BIO的方式监听Socket，如有数据，调用对应服务的实现类执行任务，将结果返回给客户端
 */
public class RpcServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 8, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            ServerSocket serverSocket = new ServerSocket(8787);
            System.out.println("服务端已经启动了");

            //BIO的方式进行轮询监听Socket
            while(true){
                Socket socket = serverSocket.accept();
                //开启一个线程去处理，这个类负责的功能太复杂，以后代码重构中，这部分功能要分离出来
                threadPool.execute(() -> {
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        //解析传入请求RpcRequest报文，先获取RpcRequest
                        RpcRequest RpcRequest = (RpcRequest) objectInputStream.readObject();
                        //通过反射，和rpc中请求调用的方法名和参数类型，确定要执行的方法
                        Method method = userService.getClass().getMethod(RpcRequest.getMethodName(), RpcRequest.getParamsTypes());
                        //执行方法
                        Object invoke = method.invoke(userService, RpcRequest.getParams());
                        //封装写入RpcResponse对象
                        objectOutputStream.writeObject(RpcResponse.success(invoke));
                        objectOutputStream.flush();
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        System.out.println("read data from IO stream error.");
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("server start failed.");
        }
    }
}
