package xyz.ankairmc.ankair.server.packet.play;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class C1FPlayKeepAlivePacket implements Packet<IPlayListener> {
    private final long payload;

    public C1FPlayKeepAlivePacket(long payload) {
        this.payload = payload;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeLong(payload);
    }
}
