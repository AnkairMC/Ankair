package xyz.ankairmc.ankair.server.packet.login;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.ILoginListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

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
