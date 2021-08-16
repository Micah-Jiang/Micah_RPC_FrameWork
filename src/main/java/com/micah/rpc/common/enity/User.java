package com.micah.rpc.common.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author m.kong
 * @Date 2021/8/11 下午2:12
 * @Version 1
 * @Description
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户性别
     */
    private boolean sex;
}
