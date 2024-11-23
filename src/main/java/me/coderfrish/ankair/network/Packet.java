package me.coderfrish.ankair.network;

public interface Packet<V extends PacketListener> {
    default void read(PacketBuffer data) {}

    default void write(PacketBuffer data) {}

    default void listener(V listener) {}
}
