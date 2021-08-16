package com.micah.rpc.serialization.msg;

import lombok.AllArgsConstructor;

/**
 * @Author m.kong
 * @Date 2021/8/16 下午2:34
 * @Version 1
 * @Description
 */
@AllArgsConstructor
public enum MessageType {
    /**
     * 0表示request, 1表示response
     */
    REQUEST(0),RESPONSE(1);

    /**
     * 消息类型
     */
    private final int code;

    /**
     * 获取消息类型
     * @return 消息类型
     */
    public int getCode() {
        return code;
    }
}
