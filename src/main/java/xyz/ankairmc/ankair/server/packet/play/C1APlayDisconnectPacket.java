package xyz.ankairmc.ankair.server.packet.play;

import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.PacketBuffer;
import xyz.ankairmc.ankair.server.data.ServerDisconnectData;

public class C1APlayDisconnectPacket implements Packet<IPlayListener> {
    private final String reason;

    public C1APlayDisconnectPacket(String reason) {
        this.reason = reason;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeUtfString(new ServerDisconnectData(reason).toString());
    }
}
