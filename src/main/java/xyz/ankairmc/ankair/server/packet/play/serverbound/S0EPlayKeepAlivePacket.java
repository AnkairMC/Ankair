package xyz.ankairmc.ankair.server.packet.play.serverbound;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class S0EPlayKeepAlivePacket implements Packet<IPlayListener> {
    public long payload;

    @Override
    public void read(PacketBuffer data) {
        this.payload = data.readLong();
    }

    @Override
    public void listener(IPlayListener listener) {
        listener.handleKeepAlive(this);
    }
}
