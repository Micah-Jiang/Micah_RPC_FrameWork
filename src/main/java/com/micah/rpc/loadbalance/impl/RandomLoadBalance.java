package com.micah.rpc.loadbalance.impl;

import com.micah.rpc.loadbalance.LoadBalance;

import java.util.List;
import java.util.Random;

/**
 * @Author m.kong
 * @Date 2021/8/17 上午11:23
 * @Version 1
 * @Description
 */
public class RandomLoadBalance implements LoadBalance {
    @Override
    public String balance(List<String> addressList) {
        int size = addressList.size();
        Random random = new Random();
        return addressList.get(random.nextInt(size));
    }
}
