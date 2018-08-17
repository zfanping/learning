package netty.guide.ch12;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by frank on 2018-08-14.
 */
public class UpdServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private static final String[] dictionary = new String[]{"1", "贰", "3", "肆"};

    private String nextQuote() {
        int quoteId = ThreadLocalRandom.current().nextInt(dictionary.length);
        return dictionary[quoteId];
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String req = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println(req);

        if ("查询".equals(req)) {
            ctx.writeAndFlush(
                    new DatagramPacket(
                            Unpooled.copiedBuffer(nextQuote().getBytes(CharsetUtil.UTF_8)),
                            packet.sender()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
