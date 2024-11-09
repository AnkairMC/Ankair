package xyz.ankairmc.ankair.server.packet.login;

import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.ILoginListener;
import xyz.ankairmc.ankair.protocol.PacketBuffer;
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
