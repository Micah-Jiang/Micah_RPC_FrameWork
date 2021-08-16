package com.micah.rpc.client;

import com.micah.rpc.common.msg.RpcRequest;
import com.micah.rpc.common.msg.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午4:00
 * @Version 1
 * @Description
 */
public class NettyRpcClient implements RpcClient {
    private static final Bootstrap BOOTSTRAP;
    private static final EventLoopGroup EVENT_LOOP_GROUP;
    private final String host;
    private final int port;

    public NettyRpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // netty客户端初始化，重复使用
    static {
        EVENT_LOOP_GROUP = new NioEventLoopGroup();
        BOOTSTRAP = new Bootstrap();
        BOOTSTRAP.group(EVENT_LOOP_GROUP).channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }

    /**
     * 这里需要操作一下，因为netty的传输都是异步的，你发送request，会立刻返回一个值， 而不是想要的相应的response
     */
    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        try {
            ChannelFuture channelFuture  = BOOTSTRAP.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            // 发送数据
            channel.writeAndFlush(request);
            channel.closeFuture().sync();
            // 阻塞的获得结果，通过给channel设计别名，获取特定名字下的channel中的内容（这个在handler中设置）
            // AttributeKey是，线程隔离的，不会由线程安全问题。
            // 实际上不应通过阻塞，可通过回调函数
            AttributeKey<RpcResponse> key = AttributeKey.valueOf("RpcResponse");
            RpcResponse response = channel.attr(key).get();

            System.out.println("response test ---> " + response);
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
