package xyz.ankairmc.ankair.protocol.types;

import io.netty.buffer.ByteBuf;

public class VarInt {
    public static int read(ByteBuf buf) {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = buf.readByte();
            int value = read & 0b01111111;
            result |= value << 7 * numRead;
            numRead++;
            if (numRead > 5) { throw new RuntimeException("VarInt is too big"); }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    public static ByteBuf write(ByteBuf buf, int value) {
        do {
            byte temp = (byte) (value & 0b01111111);
            value >>>= 7;
            if (value != 0) {
                temp |= (byte) 0b10000000;
            }
            buf.writeByte(temp);
        } while (value != 0);
        return buf;
    }
}
