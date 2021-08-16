package com.micah.rpc.client;

import com.micah.rpc.common.msg.RpcRequest;
import com.micah.rpc.common.msg.RpcResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author m.kong
 * @Date 2021/8/12 上午12:08
 * @Version 1
 * @Description 动态代理
 */
@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    private final RpcClient client;

    /**
     * AOP底层实现：jdk 动态代理， 每一次代理对象调用方法，会经过此方法增强（反射获取request对象，socket发送至客户端）
     * @param proxy 代理对象
     * @param method 方法
     * @param args 方法参数集合
     * @return 服务端处理结果
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // request的构建，使用了lombok中的builder，代码简洁
        RpcRequest request = RpcRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args).paramsTypes(method.getParameterTypes()).build();
        // 数据传输
        RpcResponse response = client.sendRequest(request);
        return response.getData();
    }

    /**
     * 通过反射获取到代理对象
     * @param clazz 需要反射的对象
     * @param <T> 泛型参数
     * @return 返回代理对象
     */
    public <T>T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}
