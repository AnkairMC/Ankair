package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public record ClientBoundGameKeepAlivePacket(long payload) implements Packet<ITCPGameListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writeLong(payload);
    }
}
