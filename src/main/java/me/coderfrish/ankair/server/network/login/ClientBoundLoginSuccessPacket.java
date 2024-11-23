package me.coderfrish.ankair.server.network.login;

import me.coderfrish.ankair.core.GameProfile;
import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

import java.util.UUID;

public record ClientBoundLoginSuccessPacket(String username, UUID uuid) implements Packet<ITCPLoginListener> {
    public ClientBoundLoginSuccessPacket(GameProfile profile) {
        this(profile.getUsername(), profile.getUuid());
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeString(uuid.toString(), 36);
        data.writeString(username, 16);
    }
}
