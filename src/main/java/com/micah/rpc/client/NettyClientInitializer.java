package com.micah.rpc.client;

import com.micah.rpc.serialization.MicahDecode;
import com.micah.rpc.serialization.MicahEncode;
import com.micah.rpc.serialization.serializer.JsonSerializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午4:21
 * @Version 1
 * @Description 同样的与服务端解码和编码格式
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        // 使用自定义的编解码器
        pipeline.addLast(new MicahDecode());
        // 编码需要传入序列化器，这里是json，还支持ObjectSerializer，也可以自己实现其他的
        pipeline.addLast(new MicahEncode(new JsonSerializer()));
        pipeline.addLast(new NettyClientHandler());
    }
}
