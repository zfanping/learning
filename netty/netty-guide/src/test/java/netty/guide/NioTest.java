package netty.guide;

import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;

/**
 * Created by frank on 2018-08-16.
 */
public class NioTest extends TestCase {
    public void testBuffer() {
        int capacity = 48;
        // 初始化
        ByteBuffer buffer = ByteBuffer.allocate(capacity);
        assertEquals(capacity, buffer.capacity());
        assertEquals(capacity, buffer.limit());
        assertEquals(0, buffer.position());

        // 切换到读模式
        buffer.flip();
        assertEquals(0, buffer.limit());
        assertEquals(0, buffer.position());
        assertFalse(buffer.hasRemaining());

        // 切换到写模式
        buffer.compact();
        assertEquals(capacity, buffer.limit());
        assertEquals(0, buffer.position());

        buffer.put((byte) 0xF0);
        assertEquals(1, buffer.position());
        assertTrue(buffer.hasRemaining());

        buffer.putShort((short) 0xF1F1);
        assertEquals(3, buffer.position());

        buffer.put((byte) 0xF2);
        assertEquals(4, buffer.position());

        // 切换成读模式
        buffer.flip();
        assertEquals(4, buffer.limit());
        assertEquals(0, buffer.position());

        assertEquals((short) 0xF0F1, buffer.getShort());
        assertEquals(4, buffer.limit());
        assertEquals(2, buffer.position());

        assertEquals((byte) 0xF1, buffer.get());
        assertEquals(4, buffer.limit());
        assertEquals(3, buffer.position());

        // 重新读
        buffer.rewind();
        assertEquals(4, buffer.limit());
        assertEquals(0, buffer.position());

        assertEquals((byte) 0xF0, buffer.get());
        assertEquals(4, buffer.limit());
        assertEquals(1, buffer.position());

        // mark & reset
        buffer.mark();
        assertEquals((byte) 0xF1, buffer.get());
        assertEquals(4, buffer.limit());
        assertEquals(2, buffer.position());

        buffer.reset();
        assertEquals(4, buffer.limit());
        assertEquals(1, buffer.position());

        // compact
        buffer.compact();
        assertEquals(capacity, buffer.limit());
        assertEquals(3, buffer.position());

    }

    public void test_buffer_illegal() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put(new byte[]{(byte) 0xF0, (byte) 0xF1, (byte) 0xF2});
        assertEquals(3, buffer.position());

        assertEquals((byte) 0, buffer.get());
        assertEquals(4, buffer.position());

        buffer.flip();
        assertEquals(0, buffer.position());
        assertEquals(4, buffer.limit());

        assertEquals((byte) 0xF0, buffer.get());
        assertEquals(1, buffer.position());

        buffer.put((byte) 0xF4);
        assertEquals(2, buffer.position());
        assertEquals(4, buffer.limit());

    }

    public void test_transfer() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);

    }

    public void test_pipe() throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sink = pipe.sink();

        String newData = "New String to write to file..." + System.currentTimeMillis();
        byte[] byteArray = newData.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(byteArray.length);
        buffer.put(byteArray);

        buffer.flip();
        while (buffer.hasRemaining()) {
            sink.write(buffer);
        }

        Pipe.SourceChannel source = pipe.source();

        buffer.clear();
        source.read(buffer);

        buffer.flip();

        assertEquals(newData, new String(buffer.array()));

    }
}
