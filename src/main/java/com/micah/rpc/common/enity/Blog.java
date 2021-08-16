package com.micah.rpc.common.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author m.kong
 * @Date 2021/8/12 上午10:47
 * @Version 1
 * @Description 博客类
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
    /**
     * 博客ID
     */
    private Integer id;
    /**
     * 博客标题
     */
    private String title;
    /**
     * 博文内容
     */
    private String content;
    /**
     * 作者ID
     */
    private Integer userId;
}
