package netty.guide.ch14.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import netty.guide.ch14.MessageType;
import netty.guide.ch14.struct.Header;
import netty.guide.ch14.struct.NettyMessage;
import sun.nio.ch.Net;

/**
 * Created by frank on 2018-08-16.
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println("Server receive client heart beat message: " + message);
            NettyMessage heartBeat = buildHeartBeat();
            System.out.println("Server send hear beat response to client: " + message);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeartBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }

}
