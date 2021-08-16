package com.micah.rpc.client.old;

import com.micah.rpc.common.msg.RpcRequest;
import com.micah.rpc.common.msg.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author m.kong
 * @Date 2021/8/11 下午7:17
 * @Version 1
 * @Description 负责与服务端底层的通信
 */
public class IoClient {
    /**
     * 这里负责底层与服务端的通信，发送的Request，接受的是Response对象
     * 客户端发起一次请求调用，Socket建立连接，发起请求Request，得到响应Response
     * 这里的request是封装好的（上层进行封装），不同的service需要进行不同的封装， 客户端只知道Service接口，需要一层动态代理根据反射封装不同的Service
     * @param host 服务端IP
     * @param port 服务端端口
     * @param request 请求的封装实例
     * @return 服务端处理结果，包括状态码和处理结果等信息
     */
    public static RpcResponse sendRequest(String host, int port, RpcRequest request){
        try {
            //连接服务端
            Socket socket = new Socket(host, port);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println(request);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            return (RpcResponse) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("无法连接服务器：" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
