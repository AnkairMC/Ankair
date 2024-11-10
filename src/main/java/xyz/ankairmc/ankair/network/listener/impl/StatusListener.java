package xyz.ankairmc.ankair.network.listener.impl;

import xyz.ankairmc.ankair.MinecraftServer;
import xyz.ankairmc.ankair.network.listener.IStatusListener;
import xyz.ankairmc.ankair.player.Player;
import xyz.ankairmc.ankair.server.MinecraftStatus;
import xyz.ankairmc.ankair.server.data.ServerStatusData;
import xyz.ankairmc.ankair.server.packet.status.serverbound.S01StatusPingPacket;
import xyz.ankairmc.ankair.server.packet.status.serverbound.S00StatusRequestPacket;
import xyz.ankairmc.ankair.server.packet.status.clientbound.C00StatusResponsePacket;
import xyz.ankairmc.ankair.server.packet.status.clientbound.C01StatusPongPacket;

public class StatusListener implements IStatusListener {
    private final Player session;

    public StatusListener(Player session) {
        this.session = session;
    }

    @Override
    public void handleStatusRequest(S00StatusRequestPacket packet) {
        MinecraftStatus status = MinecraftServer.getServer();
        session.sendPacket(new C00StatusResponsePacket(
                new ServerStatusData(
                        new ServerStatusData.Version(status.getVersionName(), status.getProtocol()),
                        new ServerStatusData.Players(status.getMax(), status.getOnline()),
                        new ServerStatusData.Description(status.getMotd())
                )
        ));
    }

    @Override
    public void handleStatusPing(S01StatusPingPacket packet) {
        session.sendPacket(new C01StatusPongPacket(packet.payload));
        session.disconnect();
    }
}
