package xyz.ankairmc.ankair.server.packet.login;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.ILoginListener;
import xyz.ankairmc.ankair.protocol.types.UtfString;

public class S00LoginStartPacket implements Packet<ILoginListener> {
    public String username;

    @Override
    public void read(ByteBuf data) {
        this.username = UtfString.read(data);
    }

    @Override
    public void listener(ILoginListener listener) {
        listener.handleLoginStart(this);
    }
}
