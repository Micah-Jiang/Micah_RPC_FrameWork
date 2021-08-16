package com.micah.rpc.client.impl;

import com.micah.rpc.client.RpcClient;
import com.micah.rpc.common.msg.RpcRequest;
import com.micah.rpc.common.msg.RpcResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午2:32
 * @Version 1
 * @Description
 */
@AllArgsConstructor
public class RpcClientSample implements RpcClient {
    private final String host;
    private final int port;

    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        try {
            Socket socket = new Socket(host, port);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeObject(request);
            outputStream.flush();

            return  (RpcResponse) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
