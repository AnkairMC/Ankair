package xyz.ankairmc.ankair.server.packet.play.clientbound;

import xyz.ankairmc.ankair.core.Position;
import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class C49PlaySpawnPositionPacket implements Packet<IPlayListener> {
    private final int x;
    private final int y;
    private final int z;

    public C49PlaySpawnPositionPacket(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writePosition(new Position(x, y, z));
    }
}
