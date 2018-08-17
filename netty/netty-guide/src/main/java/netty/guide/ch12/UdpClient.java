package netty.guide.ch12;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Created by frank on 2018-08-14.
 */
public class UdpClient {
    public static void main(String[] args) throws InterruptedException {
        new UdpClient().run(1212);
    }

    public void run(int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UpdClientHandler());

            Channel channel = b.bind(0).sync().channel();

            // 向网段内的所有机器广播UPD消息
            channel.writeAndFlush(
                    new DatagramPacket(
                            Unpooled.copiedBuffer("查询", CharsetUtil.UTF_8),
                            new InetSocketAddress("255.255.255.255", port)))
                    .sync();

            if (!channel.closeFuture().sync().await(15000)) {
                System.out.println("查询超时");
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
