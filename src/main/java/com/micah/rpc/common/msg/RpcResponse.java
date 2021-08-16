package com.micah.rpc.common.msg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author m.kong
 * @Date 2021/8/11 下午6:24
 * @Version 1
 * @Description
 */
@Data
@Builder
@AllArgsConstructor
public class RpcResponse implements Serializable {
    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String message;
    /**
     * 具体数据
     */
    private Object data;
    /**
     * Update 8.16
     * 更新,这里我们需要加入这个，不然用其它序列化方式（除了java Serialize）得不到data的type
     */
    private Class<?> dataType;

    /**
     * 执行成功
     * @param data 封装数据
     * @return RpcResponse实例
     */
    public static RpcResponse success(Object data){
        return RpcResponse.builder().code(200).data(data).dataType(data.getClass()).build();
    }

    /**
     * 执行失败
     * @return RpcResponse实例
     */
    public static RpcResponse failed(){
        return RpcResponse.builder().code(500).message("服务器发生错误").build();
    }
}
