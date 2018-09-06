package nia.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by wshcatkin on 2018-08-19.
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("[" + ctx.channel().remoteAddress() + "] " + "channelRead0");

        Channel channel = ctx.channel();

        for (Channel ch : group) {
            if (ch == channel) {
                ctx.writeAndFlush("[you]: " + msg + "\n");
            } else {
                ch.writeAndFlush("[" + channel.remoteAddress() + "]: " + msg + "\n");
            }
        }
        System.out.println("[" + channel.remoteAddress() + "]: " + msg + "\n");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[" + ctx.channel().remoteAddress() + "] " + "handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[" + ctx.channel().remoteAddress() + "] " + "handlerRemoved");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[" + channel.remoteAddress() + "] " + "offline");

        for (Channel ch : group) {
            ch.writeAndFlush(
                    "[" + channel.remoteAddress() + "] " + "is leaving");
        }
        group.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[" + channel.remoteAddress() + "] " + "online");
        ctx.writeAndFlush("[server]: welcome");

        for (Channel ch : group) {
            ch.writeAndFlush(
                    "[" + channel.remoteAddress() + "] " + "is coming");
        }
        group.add(channel);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[" + ctx.channel().remoteAddress() + "] " + "registered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[" + ctx.channel().remoteAddress() + "] " + "unregistered");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[" + ctx.channel().remoteAddress() + "] " + "read complete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println(
                "[" + ctx.channel().remoteAddress() + "]" + "exit the room");
        ctx.close().sync();
    }
}
