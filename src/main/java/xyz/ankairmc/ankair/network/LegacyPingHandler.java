package xyz.ankairmc.ankair.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xyz.ankairmc.ankair.MinecraftServer;
import xyz.ankairmc.ankair.server.MinecraftStatus;

import java.nio.charset.StandardCharsets;

public class LegacyPingHandler extends SimpleChannelInboundHandler<ByteBuf> {
    public LegacyPingHandler() {
        super(false);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf input) {
        MinecraftStatus status = MinecraftServer.getServer();
        int id = input.readUnsignedByte();

        try {
            if (id == 0xFE) {
                ByteBuf bytebuf = ctx.alloc().buffer();
                String response = String.format(
                        "\u00a71\u0000%d\u0000%s\u0000%s\u0000%d\u0000%d",
                        status.getProtocol(),
                        status.getVersionName(),
                        status.getMotd(),
                        status.getOnline(),
                        status.getMax()
                );

                bytebuf.writeByte(255);
                bytebuf.writeShort(response.length());
                bytebuf.writeCharSequence(response, StandardCharsets.UTF_16BE);

                ctx.writeAndFlush(bytebuf).addListener(ChannelFutureListener.CLOSE);
            }
        } finally {
            input.resetReaderIndex();
        }

        ctx.channel().pipeline().remove(this);
        ctx.fireChannelRead(input);
    }
}
