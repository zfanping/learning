package nia.ch10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by frank on 2018-09-29.
 */
public class ToIntegerDecoderTest extends TestCase {
    public void test() throws Exception {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel();

        embeddedChannel.pipeline().addLast(new ToIntegerDecoder());

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        buf.writeInt(2);
        buf.writeInt(3);
        buf.writeInt(5);

        embeddedChannel.writeInbound(buf);
        assertEquals(true, embeddedChannel.finish());

        assertEquals(1, embeddedChannel.readInbound());
        assertEquals(2, embeddedChannel.readInbound());
        assertEquals(3, embeddedChannel.readInbound());
        assertEquals(5, embeddedChannel.readInbound());
        assertEquals(null, embeddedChannel.readInbound());
    }

    public void test2() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel();

        embeddedChannel.pipeline().addLast(new ToIntegerDecoder())
                .addLast(new IntegerToStringDecoder());

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        buf.writeInt(2);
        buf.writeInt(3);
        buf.writeInt(5);
        buf.writeByte(6);

        embeddedChannel.writeInbound(buf);
        assertEquals(true, embeddedChannel.finish());

        assertEquals("1", embeddedChannel.readInbound());
        assertEquals("2", embeddedChannel.readInbound());
        assertEquals("3", embeddedChannel.readInbound());
        assertEquals("5", embeddedChannel.readInbound());
        assertEquals(null, embeddedChannel.readInbound());
    }
}