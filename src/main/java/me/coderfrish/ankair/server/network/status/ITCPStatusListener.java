package me.coderfrish.ankair.server.network.status;

import me.coderfrish.ankair.network.PacketListener;

public interface ITCPStatusListener extends PacketListener {
    void handleStatusRequest(ServerBoundStatusRequestPacket packet);

    void handleStatusPing(ServerBoundStatusPingPacket packet);
}
