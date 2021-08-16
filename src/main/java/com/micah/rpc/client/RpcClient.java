package com.micah.rpc.client;

import com.micah.rpc.common.msg.RpcRequest;
import com.micah.rpc.common.msg.RpcResponse;

import java.io.IOException;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午2:29
 * @Version 1
 * @Description
 */
public interface RpcClient {
    /**
     * 发送请求
     * @param request 请求信息封装实例
     * @return RpcResponse
     */
    RpcResponse sendRequest(RpcRequest request);
}
