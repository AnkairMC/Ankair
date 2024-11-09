package xyz.ankairmc.ankair.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.packet.PacketDirection;
import xyz.ankairmc.ankair.network.packet.PacketListener;
import xyz.ankairmc.ankair.player.Player;

public class MessagePacketEncoder extends MessageToByteEncoder<Packet<? extends PacketListener>> {
    @Override
    @SuppressWarnings("unchecked")
    protected void encode(ChannelHandlerContext ctx, Packet<? extends PacketListener> packet, ByteBuf output) {
        Player session = ctx.channel().attr(NetworkManager.session).get();
        int id = ConnectionStatus.getIdByPacket(session.getStatus(), PacketDirection.CLIENT_BOUND, (Class<? extends Packet<? extends PacketListener>>) packet.getClass());

        PacketBuffer packetBuffer = new PacketBuffer(output);
        packetBuffer.writeVarInt(id);
        packet.write(packetBuffer);
    }
}
