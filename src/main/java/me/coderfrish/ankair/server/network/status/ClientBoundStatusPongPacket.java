package me.coderfrish.ankair.server.network.status;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public record ClientBoundStatusPongPacket(long payload) implements Packet<ITCPStatusListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writeLong(payload);
    }
}
