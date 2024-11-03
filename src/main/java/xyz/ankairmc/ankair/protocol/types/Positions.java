package xyz.ankairmc.ankair.protocol.types;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.core.Position;

public class Positions {
    public static ByteBuf write(ByteBuf buf, Position position) {
        buf.writeLong(position.toLong());
        return buf;
    }

    public static Position read(ByteBuf buf) {
        return Position.toPosition(buf.readLong());
    }
}
