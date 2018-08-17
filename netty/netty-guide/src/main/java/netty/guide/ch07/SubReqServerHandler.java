package netty.guide.ch07;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * Created by frank on 2018-08-13.
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        System.out.println(req);
        ctx.writeAndFlush(buildResp(req.getId()));
    }

    private Object buildResp(int id) {
        SubscribeResp resp = new SubscribeResp();
        resp.setId(id);
        resp.setRespCode(0);
        resp.setDesc("success");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
