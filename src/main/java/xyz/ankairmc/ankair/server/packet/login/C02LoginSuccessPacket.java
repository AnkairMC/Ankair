package xyz.ankairmc.ankair.server.packet.login;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IStatusListener;
import xyz.ankairmc.ankair.protocol.types.UtfString;

import java.util.UUID;

public class C02LoginSuccessPacket implements Packet<IStatusListener> {
    private final UUID uuid;
    private final String username;

    public C02LoginSuccessPacket(UUID uuid, String username) {
        this.uuid = uuid;
        this.username = username;
    }

    @Override
    public void write(ByteBuf data) {
        UtfString.write(data, uuid.toString(), 36);
        UtfString.write(data, username, 16);
    }
}
