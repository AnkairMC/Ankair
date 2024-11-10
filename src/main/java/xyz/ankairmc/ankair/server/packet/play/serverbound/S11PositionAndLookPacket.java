package xyz.ankairmc.ankair.server.packet.play.serverbound;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class S11PositionAndLookPacket implements Packet<IPlayListener> {
    @Override
    public void read(PacketBuffer data) {
        Packet.super.read(data);
    }

    @Override
    public void listener(IPlayListener listener) {
        listener.handlePlayerPositionAndLookPacket(this);
    }
}
