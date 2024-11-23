package me.coderfrish.ankair.server.network.status;

import me.coderfrish.ankair.network.Packet;

public class ServerBoundStatusRequestPacket implements Packet<ITCPStatusListener> {
    @Override
    public void listener(ITCPStatusListener listener) {
        listener.handleStatusRequest(this);
    }
}
