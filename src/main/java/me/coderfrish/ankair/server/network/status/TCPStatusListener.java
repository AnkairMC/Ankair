package me.coderfrish.ankair.server.network.status;

import io.netty.channel.ChannelFutureListener;
import me.coderfrish.ankair.MinecraftServer;
import me.coderfrish.ankair.network.ClientConnection;
import me.coderfrish.ankair.server.IServerDescription;
import me.coderfrish.ankair.server.network.status.data.ServerStatusResponse;

public class TCPStatusListener implements ITCPStatusListener {
    private final ClientConnection connection;

    public TCPStatusListener(ClientConnection connection) {
        this.connection = connection;
    }

    @Override
    public void handleStatusRequest(ServerBoundStatusRequestPacket packet) {
        IServerDescription description = MinecraftServer.getServer();
        connection.sendPacket(new ClientBoundStatusResponsePacket(
                new ServerStatusResponse(
                        new ServerStatusResponse.Version(
                                description.getServerProtocol(),
                                description.getServerName()
                        ),
                        new ServerStatusResponse.Players(
                                description.getPlayersMax(),
                                description.getPlayersOnline()
                        ),
                        new ServerStatusResponse.Description(
                                description.getServerDescription()
                        )
                )
        ));
    }

    @Override
    public void handleStatusPing(ServerBoundStatusPingPacket packet) {
        connection.sendPacket(
                new ClientBoundStatusPongPacket(packet.payload),
                (ChannelFutureListener) _ -> this.disconnect("")
        );
    }

    @Override
    public void disconnect(String reason) {
        this.connection.disconnect();
    }
}
