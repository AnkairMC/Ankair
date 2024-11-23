package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.network.PacketListener;

public interface ITCPGameListener extends PacketListener {
    void handleKeepAlive(ServerBoundGameKeepAlivePacket packet);

    void handleClientSettings(ServerBoundGameClientSettingsPacket packet);

    void handleTeleportConfirm(ServerBoundTeleportConfirmPacket packet);

    void handlePlayerPositionRotation(ServerBoundGamePlayerPositionRotationPacket packet);
}
