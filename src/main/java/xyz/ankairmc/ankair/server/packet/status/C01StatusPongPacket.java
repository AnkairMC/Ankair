package xyz.ankairmc.ankair.server.packet.status;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IStatusListener;

public class C01StatusPongPacket implements Packet<IStatusListener> {
    private final long payload;

    public C01StatusPongPacket(long payload) {
        this.payload = payload;
    }

    @Override
    public void write(ByteBuf data) {
        data.writeLong(this.payload);
    }
}
