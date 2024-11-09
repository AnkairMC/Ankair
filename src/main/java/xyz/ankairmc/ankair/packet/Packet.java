package xyz.ankairmc.ankair.packet;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.protocol.PacketBuffer;

public interface Packet<T extends PacketListener> {
    default void read(PacketBuffer data) {}

    default void write(PacketBuffer data) {}

    default void listener(T listener) {}
}
