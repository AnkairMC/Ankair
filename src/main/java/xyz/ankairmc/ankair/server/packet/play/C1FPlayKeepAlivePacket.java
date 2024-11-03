package xyz.ankairmc.ankair.server.packet.play;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;

public class C1FPlayKeepAlivePacket implements Packet<IPlayListener> {
    private final long payload;

    public C1FPlayKeepAlivePacket(long payload) {
        this.payload = payload;
    }

    @Override
    public void write(ByteBuf data) {
        data.writeLong(payload);
    }
}
