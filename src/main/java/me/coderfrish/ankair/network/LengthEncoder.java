package me.coderfrish.ankair.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class LengthEncoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf input, ByteBuf output) {
        PacketBuffer buffer = new PacketBuffer(output);
        buffer.writeVarInt(input.writerIndex());
        buffer.writeBytes(input);
    }
}
