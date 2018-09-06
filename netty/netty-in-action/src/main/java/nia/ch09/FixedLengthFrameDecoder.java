package nia.ch09;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by wshcatkin on 2018-08-18.
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {
    private int frameLenth;

    public FixedLengthFrameDecoder(int frameLenth) {
        this.frameLenth = frameLenth;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() >= frameLenth) {
            ByteBuf buf = in.readBytes(frameLenth);
            out.add(buf);
        }
    }
}
