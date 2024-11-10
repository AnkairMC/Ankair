package xyz.ankairmc.ankair.server.packet.login.clientbound;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IStatusListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

import java.util.UUID;

public class C02LoginSuccessPacket implements Packet<IStatusListener> {
    private final UUID uuid;
    private final String username;

    public C02LoginSuccessPacket(UUID uuid, String username) {
        this.uuid = uuid;
        this.username = username;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeUtfString(uuid.toString(), 36);
        data.writeUtfString(username, 16);
    }
}
