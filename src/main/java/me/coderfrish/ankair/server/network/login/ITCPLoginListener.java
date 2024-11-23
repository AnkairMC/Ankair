package me.coderfrish.ankair.server.network.login;

import me.coderfrish.ankair.network.PacketListener;

public interface ITCPLoginListener extends PacketListener {
    void handleLoginStart(ServerBoundLoginStartPacket packet);
}
