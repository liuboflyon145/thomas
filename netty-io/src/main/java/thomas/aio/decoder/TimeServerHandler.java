package thomas.aio.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by liubo on 16/6/16.
 */
public class TimeServerHandler extends ChannelHandlerAdapter{
    private int counter;
    private static final Logger logger = Logger.getLogger(TimeServerHandler.class.getName());
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        logger.info("The Time server receive order: "+body+" ;the counter is : "+ ++counter);
        String currentTime = "query time order".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"bad order";
        currentTime = currentTime+System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }
}
