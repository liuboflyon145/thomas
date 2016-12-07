package thomas.aio.netty.endecoder;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * Created by liubo on 16/6/17.
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {
    private static final Logger logger = Logger.getLogger(SubReqServerHandler.class.getName());
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        if ("thomas".equalsIgnoreCase(req.getUserName())){
            logger.info("Server accept client subscribe req : ["+req.toString()+"]");
            ctx.writeAndFlush(resp(req.getSubRedID()));
        }
    }

    private SubscribeResp resp(int subRedID) {
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(subRedID);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed , 3 days later , sent to the designated address");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
