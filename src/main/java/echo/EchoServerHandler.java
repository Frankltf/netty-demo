package echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @Intro
 * @Author liutengfei
 * @Date 2019-03-27 14:54
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("EchoServerHandler start read ========");
            //第一种
//        Channel channel = ctx.channel();
//        channel.writeAndFlush(Unpooled.copiedBuffer("come from server ", CharsetUtil.UTF_8));
            //第二种
//        ChannelPipeline channelPipeline = ctx.pipeline();
//        channelPipeline.writeAndFlush(Unpooled.copiedBuffer("come from server ", CharsetUtil.UTF_8));
            //第三种
            ctx.writeAndFlush(Unpooled.copiedBuffer("come from server 333",CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoServerHandler complete ============");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
