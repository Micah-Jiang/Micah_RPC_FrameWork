package com.micah.rpc.server.work;

import com.micah.rpc.common.msg.RpcRequest;
import com.micah.rpc.common.msg.RpcResponse;
import com.micah.rpc.server.provider.ServiceProvider;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午12:56
 * @Version 1
 * @Description 工作线程
 */
@AllArgsConstructor
public class WorkThread implements Runnable{
    private final Socket socket;
    private final ServiceProvider serviceProvider;

    @Override
    public void run() {
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) objectInputStream.readObject();
            RpcResponse response = getResponse(request);
            objectOutputStream.writeObject(response);
            objectOutputStream.flush();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public RpcResponse getResponse(RpcRequest request){
        //获取到服务名
        String serviceName = request.getInterfaceName();
        //根据服务名找到对应的实现类
        Object service = serviceProvider.getService(serviceName);
        Method method;
        try {
            method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invoke = method.invoke(service, request.getParams());
            return RpcResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("方法执行失败");
            return RpcResponse.failed();
        }
    }
}
