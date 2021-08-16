package com.micah.rpc.client.old;

import com.micah.rpc.client.ClientProxy;
import com.micah.rpc.common.enity.Blog;
import com.micah.rpc.common.enity.User;
import com.micah.rpc.service.BlogService;
import com.micah.rpc.service.UserService;

/**
 * @Author m.kong
 * @Date 2021/8/11 下午2:09
 * @Version 1
 * @Description 客户端
 */
public class RpcClient {
    public static void main(String[] args) {
        /*ClientProxy clientProxy = new ClientProxy("localhost", 8787);
        //通过反射获取到类
        UserService userService = clientProxy.getProxy(UserService.class);
        //通过id获取User
        User user = userService.getUserById(10);
        System.out.println("Get User from server success! -- " + user);
        //插入User
        Integer insertUserRes = userService.insertUser(User.builder().id(2).username("Micah").sex(true).build());
        System.out.println("insert user into server success: " + insertUserRes);

        Blog blog = clientProxy.getProxy(BlogService.class).getBlogById(10);
        System.out.println("select blog by id --- " + blog);*/
    }
}
