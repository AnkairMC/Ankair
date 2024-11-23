package me.coderfrish.ankair.server.network.login;

import io.netty.buffer.Unpooled;
import me.coderfrish.ankair.server.IServerDescription;
import me.coderfrish.ankair.MinecraftServer;
import me.coderfrish.ankair.core.BlockPosition;
import me.coderfrish.ankair.core.GameProfile;
import me.coderfrish.ankair.core.Identifier;
import me.coderfrish.ankair.core.chat.IChatComponent;
import me.coderfrish.ankair.network.ClientConnection;
import me.coderfrish.ankair.network.ConnectionState;
import me.coderfrish.ankair.network.PacketBuffer;
import me.coderfrish.ankair.player.Location;
import me.coderfrish.ankair.player.Player;
import me.coderfrish.ankair.server.network.game.*;
import me.coderfrish.ankair.util.ProfileUtil;

import java.util.concurrent.TimeUnit;

public class TCPLoginListener implements ITCPLoginListener {
    private final IServerDescription description = MinecraftServer.getServer();
    private final Player player;
    private final ClientConnection connection;

    public TCPLoginListener(ClientConnection connection) {
        this.connection = connection;
        this.player = connection.getPlayer();
    }

    @Override
    @SuppressWarnings("all")
    public void handleLoginStart(ServerBoundLoginStartPacket packet) {
        GameProfile profile = connection.getPlayer().getProfile();
        profile.setUsername(packet.username);
        profile.setUuid(ProfileUtil.getUUIDByPlayerUsername(profile.getUsername()));

        connection.sendPacket(new ClientBoundLoginSuccessPacket(profile));
        connection.setState(ConnectionState.GAME);
        connection.setListener(new TCPGameListener(connection));

        connection.sendPacket(new ClientBoundGameJoinGamePacket(
                connection.getPlayer(),
                description.getPlayersMax(),
                32,
                false
        ));

        connection.sendPacket(new ClientBoundGameSpawnPositionPacket(
                new BlockPosition(0, 0, 0)
        ));

        Location location = player.getLocation();
        connection.sendPacket(new ClientBoundGamePositionAndLookPacket(
                location,
                (byte) 0x01,
                connection.getTeleportId()
        ));

        connection.sendPacket(new ClientBoundGamePluginMessagePacket(
                new Identifier("minecraft", "brand"),
                new PacketBuffer(Unpooled.buffer()).writeString(description.getServerModName())
        ));

        MinecraftServer.getServer().getPlayerList().addPlayer(connection.getPlayer());
        connection.getChannel().eventLoop().scheduleAtFixedRate(
                () -> connection.sendPacket(
                        new ClientBoundGameKeepAlivePacket((int) (System.currentTimeMillis() / 10000))
                ),
                5, 10, TimeUnit.SECONDS);
    }

    @Override
    public void disconnect(String reason) {
        this.connection.sendPacket(new ClientBoundLoginDisconnectPacket(
                IChatComponent.text(reason)
        ));

        this.connection.disconnect();
    }
}
