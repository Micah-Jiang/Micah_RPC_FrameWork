package com.micah.rpc.service;

import com.micah.rpc.common.enity.Blog;

/**
 * @Author m.kong
 * @Date 2021/8/12 上午10:49
 * @Version 1
 * @Description
 */
public interface BlogService {
    /**
     * 根据ID查询到对应的博客
     * @param id 博客ID
     * @return 博客实例
     */
    Blog getBlogById(Integer id);
}
