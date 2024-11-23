package me.coderfrish.ankair.server.network.login;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public class ServerBoundLoginStartPacket implements Packet<ITCPLoginListener> {
    public String username;

    @Override
    public void read(PacketBuffer data) {
        this.username = data.readString(16);
    }

    @Override
    public void listener(ITCPLoginListener listener) {
        listener.handleLoginStart(this);
    }
}
