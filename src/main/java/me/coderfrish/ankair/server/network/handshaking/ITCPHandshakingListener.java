package me.coderfrish.ankair.server.network.handshaking;

import me.coderfrish.ankair.network.PacketListener;

public interface ITCPHandshakingListener extends PacketListener {
    void handleHandshaking(ServerBoundHandshakingPacket packet);
}
