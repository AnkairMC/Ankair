package me.coderfrish.ankair.server.network.status;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public class ServerBoundStatusPingPacket implements Packet<ITCPStatusListener> {
    public long payload;

    @Override
    public void read(PacketBuffer data) {
        this.payload = data.readLong();
    }

    @Override
    public void listener(ITCPStatusListener listener) {
        listener.handleStatusPing(this);
    }
}
