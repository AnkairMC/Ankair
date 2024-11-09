package xyz.ankairmc.ankair.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class LengthFrameEncoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf input, ByteBuf output) {
        new PacketBuffer(output).writeVarInt(input.writerIndex());
        output.writeBytes(input);
    }
}
