package com.micah.rpc.server.controller;

import com.micah.rpc.server.provider.ServiceProvider;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;

/**
 * @Author m.kong
 * @Date 2021/8/12 下午3:42
 * @Version 1
 * @Description
 */
@AllArgsConstructor
public class NettyRpcServer implements RpcServer {
    private final ServiceProvider serviceProvider;

    @Override
    public void start(int port) {
        //netty 服务线程组boss负责建立连接，用户接受服务端的连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建work数组，用于进行客户端的SocketChannel的数据读写
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        System.out.println("Netty服务端启动了");
        try{
            //创建serverBootStrap对象，用户设置服务端的启动配置
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 初始化，设置使用的eventLoopGroup
            bootstrap.group(bossGroup,workGroup)
                    //设置要被实例化的 Channel 为 NioServerSocketChannel 类。
                    .channel(NioServerSocketChannel.class)
                    //设置连入服务端的 Client 的 SocketChannel 的处理器。
                    .childHandler(new NettyServerInitializer(serviceProvider));
            // 同步阻塞，绑定端口，并等待同步成功
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            // 死循环监听，监听服务端关闭，并阻塞等待
            channelFuture.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //优雅的关闭两个EventLoopGroup对象
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    @Override
    public void stop() {

    }
}
