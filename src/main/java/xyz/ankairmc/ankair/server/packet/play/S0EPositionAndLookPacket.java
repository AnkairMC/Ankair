package xyz.ankairmc.ankair.server.packet.play;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;

public class S0EPositionAndLookPacket implements Packet<IPlayListener> {
    @Override
    public void read(ByteBuf data) {
        Packet.super.read(data);
    }

    @Override
    public void listener(IPlayListener listener) {
        listener.handlePlayerPositionAndLookPacket(this);
    }
}
