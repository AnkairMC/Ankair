package xyz.ankairmc.ankair.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import xyz.ankairmc.ankair.network.NetworkManager;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.packet.PacketDirection;
import xyz.ankairmc.ankair.packet.PacketListener;
import xyz.ankairmc.ankair.player.Player;
import xyz.ankairmc.ankair.protocol.types.VarInt;

public class MessagePacketEncoder extends MessageToByteEncoder<Packet<? extends PacketListener>> {
    @Override
    @SuppressWarnings("unchecked")
    protected void encode(ChannelHandlerContext ctx, Packet<? extends PacketListener> packet, ByteBuf output) {
        Player session = ctx.channel().attr(NetworkManager.session).get();
        int id = ConnectionStatus.getIdByPacket(session.getStatus(), PacketDirection.CLIENT_BOUND, (Class<? extends Packet<? extends PacketListener>>) packet.getClass());

        VarInt.write(output, id);
        packet.write(output);
    }
}
