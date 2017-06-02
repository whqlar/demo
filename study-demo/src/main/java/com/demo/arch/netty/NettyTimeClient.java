package com.demo.arch.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Created by wu on 17/5/26.
 */
public class NettyTimeClient {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("127.0.0.1", 8080))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new TimeHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
            group.shutdownGracefully().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class TimeHandler extends SimpleChannelInboundHandler<ByteBuf>  {

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello,world", CharsetUtil.UTF_8));
        }

        @Override
        public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
            byte[] bytes = new byte[in.readableBytes()];
            in.readBytes(bytes);
            System.out.println("Client received: " + new String(bytes));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }

    }
}
