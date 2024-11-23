package me.coderfrish.ankair.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet<? extends PacketListener>> {
    @Override
    @SuppressWarnings("unchecked")
    protected void encode(ChannelHandlerContext ctx, Packet<? extends PacketListener> input, ByteBuf output) {
        ClientConnection connection = ctx.pipeline().get(ClientConnection.class);
        int id = ConnectionState.getIdByPacketClazz(connection.getState(), (Class<? extends Packet<? extends PacketListener>>) input.getClass());

        PacketBuffer buffer = new PacketBuffer(output);
        buffer.writeVarInt(id);
        input.write(buffer);
    }
}
