package xyz.ankairmc.ankair.server.packet.status;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IStatusListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class C01StatusPongPacket implements Packet<IStatusListener> {
    private final long payload;

    public C01StatusPongPacket(long payload) {
        this.payload = payload;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeLong(this.payload);
    }
}
