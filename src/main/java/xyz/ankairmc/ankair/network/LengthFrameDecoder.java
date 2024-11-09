package xyz.ankairmc.ankair.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

public class LengthFrameDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> output) {
        input.markReaderIndex();

        final byte[] buf = new byte[3];

        for (int i = 0; i < buf.length; i++) {
            if (!input.isReadable()) {
                input.resetReaderIndex();
                return;
            }

            buf[i] = input.readByte();
            if (buf[i] >= 0) {
                int length = new PacketBuffer(Unpooled.wrappedBuffer(buf)).readVarInt();

                if (input.readableBytes() < length) {
                    input.resetReaderIndex();
                } else {
                    output.add(input.readBytes(length));
                }
                return;
            }
        }

        throw new CorruptedFrameException("Length wider than 21-bit");
    }
}
