package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.core.chat.IChatComponent;
import me.coderfrish.ankair.network.ClientConnection;
import me.coderfrish.ankair.player.Player;

public class TCPGameListener implements ITCPGameListener {
    private final Player player;
    private final ClientConnection connection;

    public TCPGameListener(ClientConnection connection) {
        this.connection = connection;
        this.player = connection.getPlayer();
    }

    @Override
    public void disconnect(String reason) {
        this.connection.disconnect();

        this.connection.sendPacket(new ClientBoundGameDisconnectPacket(IChatComponent.text(reason)));
    }

    @Override
    public void handleKeepAlive(ServerBoundGameKeepAlivePacket packet) {
    }

    @Override
    public void handleClientSettings(ServerBoundGameClientSettingsPacket packet) {
    }

    @Override
    public void handleTeleportConfirm(ServerBoundTeleportConfirmPacket packet) {
    }

    @Override
    public void handlePlayerPositionRotation(ServerBoundGamePlayerPositionRotationPacket packet) {
    }
}
