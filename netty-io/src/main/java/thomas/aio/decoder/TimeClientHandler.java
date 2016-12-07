package thomas.aio.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * Created by liubo on 16/6/16.
 */
public class TimeClientHandler extends ChannelHandlerAdapter{
    private int counter;
    private byte[] req;

    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());
    public TimeClientHandler() {
        req=("query time order"+System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        logger.info("Now is : "+body+" ; the counter is : "+ ++counter);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
