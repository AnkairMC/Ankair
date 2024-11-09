package xyz.ankairmc.ankair.server.packet.play;

import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.PacketBuffer;

public class S0EPositionAndLookPacket implements Packet<IPlayListener> {
    @Override
    public void read(PacketBuffer data) {
        Packet.super.read(data);
    }

    @Override
    public void listener(IPlayListener listener) {
        listener.handlePlayerPositionAndLookPacket(this);
    }
}
