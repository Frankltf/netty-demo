package echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.awt.*;

/**
 * @Intro
 * @Author liutengfei
 * @Date 2019-03-27 14:43
 */
public class EchoServer {
    private static final int PORT = 8087;

    public static void main(String[] args){
        new EchoServer().run();
    }

    private void run(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
//                        socketChannel.pipeline().addLast(new EchoServerHandler());
                        socketChannel.pipeline().addLast(new OutboundHandler1());
                        socketChannel.pipeline().addLast(new OutboundHandler2());
                        socketChannel.pipeline().addLast(new InboundHandler1());
                        socketChannel.pipeline().addLast(new InboundHandler2());

                    }
                });
        System.out.println("echo starting ....");
        ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
        channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
