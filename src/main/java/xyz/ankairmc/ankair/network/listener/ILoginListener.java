package xyz.ankairmc.ankair.network.listener;

import xyz.ankairmc.ankair.network.packet.PacketListener;
import xyz.ankairmc.ankair.server.packet.login.S00LoginStartPacket;

public interface ILoginListener extends PacketListener {
    void handleLoginStart(S00LoginStartPacket packet);
}
