package com.micah.rpc.service.impl;

import com.micah.rpc.common.enity.User;
import com.micah.rpc.service.UserService;

import java.util.Random;
import java.util.UUID;

/**
 * @Author m.kong
 * @Date 2021/8/11 下午2:08
 * @Version 1
 * @Description
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        System.out.println("client selected the user which the id is" + id );
        //模拟从数据库中取数据
        Random random = new Random();
        return User.builder()
                .username(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean())
                .build();
    }

    @Override
    public Integer insertUser(User user) {
        System.out.println("插入数据成功：" + user);
        return 1;
    }
}
