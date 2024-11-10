package xyz.ankairmc.ankair.server.packet.status.serverbound;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IStatusListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class S01StatusPingPacket implements Packet<IStatusListener> {
    public long payload;

    @Override
    public void read(PacketBuffer data) {
        this.payload = data.readLong();
    }

    @Override
    public void listener(IStatusListener listener) {
        listener.handleStatusPing(this);
    }
}
