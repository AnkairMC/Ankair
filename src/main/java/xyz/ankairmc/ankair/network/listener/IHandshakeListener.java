package xyz.ankairmc.ankair.network.listener;

import xyz.ankairmc.ankair.network.packet.PacketListener;
import xyz.ankairmc.ankair.server.packet.handshake.S00HandshakePacket;

public interface IHandshakeListener extends PacketListener {
    void handleHandshake(S00HandshakePacket packet);
}
