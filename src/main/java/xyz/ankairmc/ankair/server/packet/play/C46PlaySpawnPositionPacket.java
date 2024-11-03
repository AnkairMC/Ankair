package xyz.ankairmc.ankair.server.packet.play;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.core.Position;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.types.Positions;

public class C46PlaySpawnPositionPacket implements Packet<IPlayListener> {
    private final int x;
    private final int y;
    private final int z;

    public C46PlaySpawnPositionPacket(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void write(ByteBuf data) {
        Positions.write(data, new Position(x, y, z));
    }
}
