package xyz.ankairmc.ankair.protocol;

import xyz.ankairmc.ankair.packet.PacketListener;
import xyz.ankairmc.ankair.server.packet.login.S00LoginStartPacket;

public interface ILoginListener extends PacketListener {
    void handleLoginStart(S00LoginStartPacket packet);
}
