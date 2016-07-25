package com.tzp.T.netty.UserGuide.demo.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * channelActive() 方法将会在连接被建立并且准备进行通信时被调用。因此让我们在这个方法里完成一个代表当前时间的32位整数消息的构建工作。
     * @param ctx
     */
	@Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        //为了发送一个新的消息，我们需要分配一个包含这个消息的新的缓冲。因为我们需要写入一个32位的整数，因此我们需要
        // 一个至少有4个字节的 ByteBuf。通过 ChannelHandlerContext.alloc() 得到一个当前的ByteBufAllocator，然后分配一个新的缓冲。
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
