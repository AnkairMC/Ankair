package xyz.ankairmc.ankair.server.packet.play;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.PacketBuffer;

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
