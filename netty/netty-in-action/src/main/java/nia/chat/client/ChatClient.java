package nia.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wshcatkin on 2018-08-19.
 */
public class ChatClient {
    public static void main(String[] args) throws IOException {
        new ChatClient().start("127.0.0.1", 8888);
    }

    public void start(String host, int port) throws IOException {
//设置一个worker线程，使用
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker);
        //指定所使用的 NIO 传输 Channel
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("stringD", new StringDecoder());
                ch.pipeline().addLast("stringC", new StringEncoder());
//                ch.pipeline().addLast("http", new HttpClientCodec());
                ch.pipeline().addLast("chat", new ChatClientHandler());
            }
        });

        try {
            //使用指定的 端口设置套 接字地址
            Channel channel = bootstrap.connect(host, port).sync().channel();
            while (true) {
                //向服务端发送内容
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));
                String input = reader.readLine();
                if (input != null) {
                    if ("quit".equals(input)) {
                        System.exit(1);
                    }
                    channel.writeAndFlush(input);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}

