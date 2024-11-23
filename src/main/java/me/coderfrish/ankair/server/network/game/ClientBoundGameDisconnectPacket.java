package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.core.chat.ChatMessageBuilder;
import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public record ClientBoundGameDisconnectPacket(ChatMessageBuilder builder) implements Packet<ITCPGameListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writeString(builder.build());
    }
}
