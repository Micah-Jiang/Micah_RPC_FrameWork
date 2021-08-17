package com.micah.rpc.loadbalance;

import java.util.List;

/**
 * @Author m.kong
 * @Date 2021/8/17 上午11:19
 * @Version 1
 * @Description 负载均衡接口
 */
public interface LoadBalance {
    /**
     * 给服务地址列表，根据不同的负载均衡策略选择一个
     * @param addressList 服务地址列表
     * @return 服务地址
     */
    String balance(List<String> addressList);
}
