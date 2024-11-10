package xyz.ankairmc.ankair.server.packet.play.clientbound;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class C32PlayPositionAndLookPacket implements Packet<IPlayListener> {
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final int tpId;

    public C32PlayPositionAndLookPacket(double x, double y, double z, float yaw, float pitch, int tpId) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.tpId = tpId;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeDouble(x);
        data.writeDouble(y);
        data.writeDouble(z);
        data.writeFloat(yaw);
        data.writeFloat(pitch);

        data.writeByte(0);
        data.writeVarInt(tpId);
    }
}
