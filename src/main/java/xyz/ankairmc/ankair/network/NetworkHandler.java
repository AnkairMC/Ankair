package xyz.ankairmc.ankair.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.packet.PacketListener;
import xyz.ankairmc.ankair.player.Player;

public class NetworkHandler extends SimpleChannelInboundHandler<Packet<? extends PacketListener>> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet<? extends PacketListener> packet) {
        this.packetRead(ctx, packet);
    }

    private void packetRead(ChannelHandlerContext ctx, Packet<? extends PacketListener> packet) {
        Player session = ctx.channel().attr(NetworkManager.session).get();

        session.read(packet);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Player session = ctx.channel().attr(NetworkManager.session).get();
        session.inActive();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        throw new RuntimeException(cause);
    }
}
