package xyz.ankairmc.ankair.server.packet.status;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IStatusListener;
import xyz.ankairmc.ankair.protocol.PacketBuffer;

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
