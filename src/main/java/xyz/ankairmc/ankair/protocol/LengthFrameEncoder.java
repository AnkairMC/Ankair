package xyz.ankairmc.ankair.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import xyz.ankairmc.ankair.protocol.types.VarInt;

public class LengthFrameEncoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf input, ByteBuf output) {
        VarInt.write(output, input.writerIndex());
        output.writeBytes(input);
    }
}
