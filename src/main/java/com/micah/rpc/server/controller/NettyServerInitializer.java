package com.micah.rpc.server.controller;

import com.micah.rpc.client.NettyClientHandler;
import com.micah.rpc.serialization.MicahDecode;
import com.micah.rpc.serialization.MicahEncode;
import com.micah.rpc.serialization.serializer.JsonSerializer;
import com.micah.rpc.server.provider.ServiceProvider;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午3:51
 * @Version 1
 * @Description 初始化，主要负责序列化的编码解码， 需要解决netty的粘包问题
 */
@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvider serviceProvider;

    @Override
    protected void initChannel(SocketChannel ch){
        ChannelPipeline pipeline = ch.pipeline();
        // 使用自定义的编解码器
        pipeline.addLast(new MicahDecode());
        // 编码需要传入序列化器，这里是json，还支持ObjectSerializer，也可以自己实现其他的
        pipeline.addLast(new MicahEncode(new JsonSerializer()));
        pipeline.addLast(new NettyClientHandler());
        pipeline.addLast(new NettyRpcServerHandler(serviceProvider));
    }
}

