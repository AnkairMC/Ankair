package xyz.ankairmc.ankair.protocol;

import xyz.ankairmc.ankair.packet.PacketListener;
import xyz.ankairmc.ankair.server.packet.handshake.S00HandshakePacket;

public interface IHandshakeListener extends PacketListener {
    void handleHandshake(S00HandshakePacket packet);
}
