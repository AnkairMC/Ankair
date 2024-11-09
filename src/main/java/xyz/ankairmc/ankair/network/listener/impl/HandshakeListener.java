package xyz.ankairmc.ankair.network.listener.impl;

import xyz.ankairmc.ankair.network.listener.IHandshakeListener;
import xyz.ankairmc.ankair.player.Player;
import xyz.ankairmc.ankair.server.packet.handshake.S00HandshakePacket;

public class HandshakeListener implements IHandshakeListener {
    private final Player session;

    public HandshakeListener(Player session) {
        this.session = session;
    }

    @Override
    public void handleHandshake(S00HandshakePacket packet) {
        session.setStatus(packet.status);
        session.setProtocol(packet.protocol);
    }
}
