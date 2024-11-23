package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public class ServerBoundGameKeepAlivePacket implements Packet<ITCPGameListener> {
    public long payload;

    @Override
    public void read(PacketBuffer data) {
        this.payload = data.readLong();
    }

    @Override
    public void listener(ITCPGameListener listener) {
        listener.handleKeepAlive(this);
    }
}
