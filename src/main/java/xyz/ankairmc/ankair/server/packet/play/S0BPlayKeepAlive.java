package xyz.ankairmc.ankair.server.packet.play;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;

public class S0BPlayKeepAlive implements Packet<IPlayListener> {
    public long payload;

    @Override
    public void read(ByteBuf data) {
        this.payload = data.readLong();
    }

    @Override
    public void listener(IPlayListener listener) {
        listener.handleKeepAlive(this);
    }
}
