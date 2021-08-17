package com.micah.rpc.loadbalance.impl;

import com.micah.rpc.loadbalance.LoadBalance;

import java.util.List;

/**
 * @Author m.kong
 * @Date 2021/8/17 上午11:29
 * @Version 1
 * @Description 轮询选择算法
 */
public class RoundLoadBalance implements LoadBalance {
    private int choose = -1;
    @Override
    public String balance(List<String> addressList) {
        choose++;
        choose = choose%addressList.size();
        return addressList.get(choose);
    }
}
