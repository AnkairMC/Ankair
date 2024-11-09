package xyz.ankairmc.ankair.network.packet;

import xyz.ankairmc.ankair.network.PacketBuffer;

public interface Packet<T extends PacketListener> {
    default void read(PacketBuffer data) {}

    default void write(PacketBuffer data) {}

    default void listener(T listener) {}
}
