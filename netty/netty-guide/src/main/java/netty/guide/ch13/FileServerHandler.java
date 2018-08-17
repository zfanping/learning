package netty.guide.ch13;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by frank on 2018-08-14.
 */
public class FileServerHandler extends SimpleChannelInboundHandler<String> {
    public static final String CRLF = System.getProperty("line.seperator");

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        File file = new File(msg);
        if (file.exists()) {
            if (!file.isFile()) {
                ctx.writeAndFlush("Not a file: " + file + CRLF);
                return;
            }

            ctx.write(String.format("%s %s %s", file, file.length(), CRLF));
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());
            ctx.write(region);
            ctx.writeAndFlush(CRLF);
        } else {
            ctx.writeAndFlush("File not found: " + file + CRLF);
        }
    }
}
