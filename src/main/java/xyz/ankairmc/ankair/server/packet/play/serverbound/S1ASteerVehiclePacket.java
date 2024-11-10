package xyz.ankairmc.ankair.server.packet.play.serverbound;

import xyz.ankairmc.ankair.network.PacketBuffer;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.packet.Packet;

public class S1ASteerVehiclePacket implements Packet<IPlayListener> {
    public float sideways;
    public float forward;
    public byte flags;

    @Override
    public void read(PacketBuffer data) {
        this.sideways = data.readFloat();
        this.forward = data.readFloat();
        this.flags = data.readByte();
    }

    @Override
    public void listener(IPlayListener listener) {
        listener.handlePlayerSteerVehicle(this);
    }
}
