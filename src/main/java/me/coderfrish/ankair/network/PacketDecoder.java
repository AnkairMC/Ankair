package me.coderfrish.ankair.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class PacketDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> output) {
        PacketBuffer buffer = new PacketBuffer(input);
        ClientConnection connection = ctx.pipeline().get(ClientConnection.class);
        int id = buffer.readVarInt();
        Class<? extends Packet<? extends PacketListener>> packetClazz = ConnectionState.getPacketClazzById(connection.getState(), id);

        try {
            if (packetClazz != null) {
                Packet<? extends PacketListener> packet = packetClazz.getConstructor().newInstance();

                packet.read(buffer);
                output.add(packet);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
