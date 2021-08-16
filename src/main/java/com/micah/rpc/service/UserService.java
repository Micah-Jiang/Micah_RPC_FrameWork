package com.micah.rpc.service;

import com.micah.rpc.common.enity.User;

/**
 * @Author m.kong
 * @Date 2021/8/11 下午2:07
 * @Version 1
 * @Description
 */
public interface UserService {
    /**
     * 根据id返回用户实例
     * @param id 用户id
     * @return 用户实例
     */
    User getUserById(Integer id);

    /**
     * 新增用户数据
     * @param user 用户user实例
     * @return 成功则返回1，失败返回0
     */
    Integer insertUser(User user);
}
