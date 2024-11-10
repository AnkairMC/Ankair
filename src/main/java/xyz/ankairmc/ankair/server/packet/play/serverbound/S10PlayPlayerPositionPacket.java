package xyz.ankairmc.ankair.server.packet.play.serverbound;

import xyz.ankairmc.ankair.network.PacketBuffer;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.packet.Packet;

public class S10PlayPlayerPositionPacket implements Packet<IPlayListener> {
    public double x;
    public double y;
    public double z;
    public boolean onGround;

    @Override
    public void read(PacketBuffer data) {
        this.x = data.readDouble();
        this.y = data.readDouble();
        this.z = data.readDouble();
        this.onGround = data.readBoolean();
    }

    @Override
    public void listener(IPlayListener listener) {
        listener.handlePlayerPosition(this);
    }
}
