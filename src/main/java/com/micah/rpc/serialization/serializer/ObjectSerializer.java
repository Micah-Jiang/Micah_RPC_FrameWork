package com.micah.rpc.serialization.serializer;

import java.io.*;

/**
 * @Author m.kong
 * @Date 2021/8/16 下午2:21
 * @Version 1
 * @Description java原生序列化
 */
public class ObjectSerializer implements Serializer {
    /**
     * java IO 对象 -> 字节数组
     * @param obj 传入对象
     * @return 字节数组
     */
    @Override
    public byte[] serializable(Object obj) {
        byte[] bytes = null;
        //字节数组输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            //对象输出流
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            //将对象流写进数组输出流
            oos.writeObject(obj);
            oos.flush();
            //字节数组输出流转成字节数组
            bytes = bos.toByteArray();
            //关闭流
            oos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    @Override
    public Object deserializable(byte[] bytes, int messageType) {
        Object obj = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public int getType() {
        //0表示java原生序列化器
        return 0;
    }
}
