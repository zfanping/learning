package netty.guide.ch14.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import netty.guide.ch14.struct.NettyMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by frank on 2018-08-15.
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {
    private MarshallingEncoder marshallingEncoder;

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> list) throws Exception {
        if (msg == null || msg.getHeader() == null) throw new Exception("The encode message is null");

        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(msg.getHeader().getCrcCode());
        sendBuf.writeInt(msg.getHeader().getLength());
        sendBuf.writeLong(msg.getHeader().getSessionId());
        sendBuf.writeByte(msg.getHeader().getType());
        sendBuf.writeByte(msg.getHeader().getPriority());
        sendBuf.writeInt(msg.getHeader().getAttachment().size());

        String key = null;
        byte[] keyArray = null;
        Object value = null;
        Map<String, Object> attachment = msg.getHeader().getAttachment();
        for (Map.Entry<String, Object> entry : attachment.entrySet()) {
            key = entry.getKey();
            keyArray = key.getBytes(CharsetUtil.UTF_8);
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = entry.getValue();
            marshallingEncoder.encode(value, sendBuf);
        }

        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), sendBuf);
        } else {
            sendBuf.writeInt(0);
            sendBuf.setInt(4, sendBuf.readableBytes());
        }
    }
}
