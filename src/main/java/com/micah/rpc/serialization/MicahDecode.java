package com.micah.rpc.serialization;

import com.micah.rpc.serialization.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author m.kong
 * @Date 2021/8/16 下午2:06
 * @Version 1
 * @Description 自定义解码器
 */
public class MicahDecode extends ByteToMessageDecoder{

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        //1、读取消息类型
        short msgType = in.readShort();
        //2、读取序列化类型
        short serializerType = in.readShort();
        //根据类型得到对应的序列化器
        Serializer serializer = Serializer.getSerializerByCode(serializerType);
        if(serializer == null) {
            throw new RuntimeException("不存在对应的序列化器");
        }
        //3、读取对象字节数组的长度
        int byteLength = in.readInt();
        //4、读取字节数组
        byte[] bytes = new byte[byteLength];
        in.readBytes(bytes);
        //5、用对应的序列化解码器解码字节数组
        Object deserialize = serializer.deserializable(bytes, msgType);
        out.add(deserialize);
    }
}
