package nia.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by wshcatkin on 2018-08-19.
 */
public class ChatServer {
    public static void main(String[] args) {
        new ChatServer().start(8888);
    }

    public void start(int port) {
        EventLoopGroup acceptor = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);

        bootstrap.group(acceptor, worker);//设置循环线程组，前者用于处理客户端连接事件，后者用于处理网络IO
        bootstrap.channel(NioServerSocketChannel.class);//用于构造socketchannel工厂
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringEncoder());
                ch.pipeline().addLast(new ChatServerHandler());
            }
        });//为处理accept客户端的channel中的pipeline添加自定义处理函数
        try {
            // 服务器绑定端口监听
            Channel channel = bootstrap.bind(port).sync().channel();
            System.out.println("server start running in port:" + port);

            // 监听服务器关闭监听
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 退出
            acceptor.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
