package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.core.Identifier;
import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public record ClientBoundGamePluginMessagePacket(Identifier channel, PacketBuffer data) implements Packet<ITCPGameListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writeIdentifier(channel);
        data.writeBytes(this.data);
    }
}
