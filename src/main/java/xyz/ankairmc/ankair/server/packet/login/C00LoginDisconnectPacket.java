package xyz.ankairmc.ankair.server.packet.login;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.ILoginListener;
import xyz.ankairmc.ankair.network.PacketBuffer;
import xyz.ankairmc.ankair.server.data.ServerDisconnectData;

public class C00LoginDisconnectPacket implements Packet<ILoginListener> {
    private final String reason;

    public C00LoginDisconnectPacket(String reason) {
        this.reason = reason;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeUtfString(new ServerDisconnectData(reason).toString());
    }
}
