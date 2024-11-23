package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public class ServerBoundGamePlayerPositionRotationPacket implements Packet<ITCPGameListener> {
    public double x, y, z;
    public float yaw, pitch;
    public boolean onGround;

    @Override
    public void read(PacketBuffer data) {
        this.x = data.readDouble();
        this.y = data.readDouble();
        this.z = data.readDouble();

        this.yaw = data.readFloat();
        this.pitch = data.readFloat();

        this.onGround = data.readBoolean();
    }

    @Override
    public void listener(ITCPGameListener listener) {
        listener.handlePlayerPositionRotation(this);
    }
}
