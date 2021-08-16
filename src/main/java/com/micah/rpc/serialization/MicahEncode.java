package com.micah.rpc.serialization;

import com.micah.rpc.common.msg.RpcRequest;
import com.micah.rpc.common.msg.RpcResponse;
import com.micah.rpc.serialization.msg.MessageType;
import com.micah.rpc.serialization.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

/**
 * @Author m.kong
 * @Date 2021/8/16 下午2:08
 * @Version 1
 * @Description 自定义编码器，依次按照自定义的消息格式写入，传入的数据为request或者response
 * 需要持有一个serialize器，负责将传入的对象序列化成字节数组
 */
@AllArgsConstructor
public class MicahEncode extends MessageToByteEncoder {

    private final Serializer serializer;

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf byteBuf){
        System.out.println(msg.getClass());
        //写入消息类型
        if (msg instanceof RpcRequest){
            byteBuf.writeShort(MessageType.REQUEST.getCode());
        }else if(msg instanceof RpcResponse){
            byteBuf.writeShort(MessageType.RESPONSE.getCode());
        }
        //写入序列化方式
        byteBuf.writeShort(serializer.getType());
        //得到序列化数组
        byte[] serializable = serializer.serializable(msg);
        //写入长度
        byteBuf.writeInt(serializable.length);
        //写入序列化字节数组
        byteBuf.writeBytes(serializable);
    }
}
