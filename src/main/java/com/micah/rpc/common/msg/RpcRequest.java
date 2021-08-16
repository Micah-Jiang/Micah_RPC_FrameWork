package com.micah.rpc.common.msg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author m.kong
 * @Date 2021/8/11 下午6:13
 * @Version 1
 * @Description 客户端那边传过来的报文格式
 */
@Data
@Builder
@AllArgsConstructor
public class RpcRequest implements Serializable {
    /**
     *服务类名，客户端只知道接口名，在服务端中用接口指向类名
     */
    private String interfaceName;
    /**
     *方法名
     */
    private String methodName;
    /**
     *参数列表
     */
    private Object[] params;
    /**
     *参数类型
     */
    private Class<?>[] paramsTypes;
}
