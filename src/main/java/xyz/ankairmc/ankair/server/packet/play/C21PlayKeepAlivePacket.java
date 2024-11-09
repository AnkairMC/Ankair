package xyz.ankairmc.ankair.server.packet.play;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class C21PlayKeepAlivePacket implements Packet<IPlayListener> {
    private final long payload;

    public C21PlayKeepAlivePacket(long payload) {
        this.payload = payload;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeLong(payload);
    }
}
