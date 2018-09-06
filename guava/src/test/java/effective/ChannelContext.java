package effective;

import java.nio.channels.Channel;

/**
 * Created by wshcatkin on 2018-09-01.
 */
public class ChannelContext {
    ThreadLocal<Channel> ctx = new ThreadLocal<>();

    public void set(Channel channel) {
        ctx.set(channel);
    }

    public Channel get() {
        return ctx.get();
    }
}
