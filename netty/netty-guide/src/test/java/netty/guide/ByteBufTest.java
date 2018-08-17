package netty.guide;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import junit.framework.TestCase;

/**
 * Created by frank on 2018-08-17.
 */
public class ByteBufTest extends TestCase {
    public void test() {
        ByteBuf buf = Unpooled.buffer();
        System.out.println(buf);
        buf.readBytes(16);

        buf.readSlice(16);

        buf.readBytes(buf);
    }
}
