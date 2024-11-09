package xyz.ankairmc.ankair.server.packet.login;

import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.ILoginListener;
import xyz.ankairmc.ankair.protocol.PacketBuffer;

public class S00LoginStartPacket implements Packet<ILoginListener> {
    public String username;

    @Override
    public void read(PacketBuffer data) {
        this.username = data.readUtfString();
    }

    @Override
    public void listener(ILoginListener listener) {
        listener.handleLoginStart(this);
    }
}
