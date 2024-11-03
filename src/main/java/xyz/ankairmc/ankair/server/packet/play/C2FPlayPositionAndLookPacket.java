package xyz.ankairmc.ankair.server.packet.play;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.types.VarInt;

public class C2FPlayPositionAndLookPacket implements Packet<IPlayListener> {
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final int tpId;

    public C2FPlayPositionAndLookPacket(double x, double y, double z, float yaw, float pitch, int tpId) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.tpId = tpId;
    }

    @Override
    public void write(ByteBuf data) {
        data.writeDouble(x);
        data.writeDouble(y);
        data.writeDouble(z);
        data.writeFloat(yaw);
        data.writeFloat(pitch);

        data.writeByte(0);
        VarInt.write(data, tpId);
    }
}
