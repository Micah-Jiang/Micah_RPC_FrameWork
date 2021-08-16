package com.micah.rpc.service.impl;

import com.micah.rpc.common.enity.Blog;
import com.micah.rpc.service.BlogService;

/**
 * @Author m.kong
 * @Date 2021/8/12 上午10:49
 * @Version 1
 * @Description
 */
public class BlogServiceImpl implements BlogService {

    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("title").content("content").userId(11).build();
        System.out.println("查询了博客：" + blog);
        return blog;
    }

}
