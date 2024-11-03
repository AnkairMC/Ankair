package xyz.ankairmc.ankair.packet;

import io.netty.buffer.ByteBuf;

public interface Packet<T extends PacketListener> {
    default void read(ByteBuf data) {}

    default void write(ByteBuf data) {}

    default void listener(T listener) {}
}
