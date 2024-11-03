package xyz.ankairmc.ankair.protocol.types;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("all")
public class UtfString {
    public static String read(ByteBuf buf) {
        return read(buf, 32767);
    }

    public static ByteBuf write(ByteBuf buf, String value) {
        return write(buf, value, 32767);
    }

    public static String read(ByteBuf buf, int length) {
        int len = VarInt.read(buf);

        if (len > length) {
            throw new RuntimeException("Exceeding Expected Values: " + length);
        } else {
            byte[] data = new byte[len];
            buf.readBytes(data);
            return new String(data, StandardCharsets.UTF_8);
        }
    }

    public static ByteBuf write(ByteBuf buf, String value, int length) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > length) {
            throw new RuntimeException("Exceeding Expected Values: " + length);
        } else {
            VarInt.write(buf, bytes.length);
            buf.writeBytes(bytes);
        }

        return buf;
    }
}
