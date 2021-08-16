package com.micah.rpc.serialization.serializer;

/**
 * @Author m.kong
 * @Date 2021/8/16 下午2:07
 * @Version 1
 * @Description 序列化器接口
 */
public interface Serializer {
    /**
     * 对象序列化为字节数组
     * @param obj 传入对象
     * @return 字节数组
     */
    byte[] serializable(Object obj);

    /**
     * 从字节数组反序列化成消息, 使用java自带序列化方式,不用messageType也能得到相应的对象（序列化字节数组里包含类信息）
     * 其它方式需指定消息格式，再根据message转化成相应的对象
     * @param bytes 字节数组
     * @param messageType 消息格式
     * @return 反序列化后的消息
     */
    Object deserializable(byte[] bytes, int messageType);

    /**
     * 返回使用的序列化器
     * @return 0: java自带序列化方式，1：json序列化方式
     */
    int getType();

    /**
     * 根据msgType类型，获取序列化器
     * 暂时有两种实现方式，需要其它方式，实现这个接口即可
     * @param code 序号
     * @return 实现方式
     */
    static Serializer getSerializerByCode(int code){
        switch (code){
            case 0:
                return new ObjectSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }

}
