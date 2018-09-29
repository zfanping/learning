package nia.ch10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by wshcatkin on 2018-08-18.
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ToIntegerDecoder channelRead: " + ByteBufUtil.hexDump((ByteBuf) msg));
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ToIntegerDecoder channelReadComplete: ");
        super.channelReadComplete(ctx);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("ToIntegerDecoder: " + ByteBufUtil.hexDump(in));
        while (in.readableBytes() >= 4) {
            out.add(in.readInt());
        }
    }
}
