package xyz.ankairmc.ankair.server.packet.status;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IStatusListener;
import xyz.ankairmc.ankair.protocol.types.UtfString;
import xyz.ankairmc.ankair.server.data.ServerStatusData;

public class C00StatusResponsePacket implements Packet<IStatusListener> {
    private final ServerStatusData data;

    public C00StatusResponsePacket(ServerStatusData data) {
        this.data = data;
    }

    @Override
    public void write(ByteBuf data) {
        UtfString.write(data, this.data.toString());
    }
}
