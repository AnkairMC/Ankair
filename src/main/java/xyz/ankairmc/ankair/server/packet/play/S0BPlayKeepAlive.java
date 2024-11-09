package xyz.ankairmc.ankair.server.packet.play;

import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.PacketBuffer;

public class S0BPlayKeepAlive implements Packet<IPlayListener> {
    public long payload;

    @Override
    public void read(PacketBuffer data) {
        this.payload = data.readLong();
    }

    @Override
    public void listener(IPlayListener listener) {
        listener.handleKeepAlive(this);
    }
}
