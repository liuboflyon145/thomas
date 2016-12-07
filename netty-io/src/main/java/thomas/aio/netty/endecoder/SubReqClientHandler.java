package thomas.aio.netty.endecoder;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * Created by liubo on 16/6/18.
 */
public class SubReqClientHandler extends ChannelHandlerAdapter{
    private static final Logger logger = Logger.getLogger(SubReqClientHandler.class.getName());
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReq subReq (int i){
        SubscribeReq req = new SubscribeReq();
        req.setAddress("北京市宣武门外大街");
        req.setPhoneNumber("18010038673");
        req.setProductName("Netty 权威指南");
        req.setSubRedID(i);
        req.setUserName("thomas");
        return req;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("Receive server response : ["+msg+"]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
