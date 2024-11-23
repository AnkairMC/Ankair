package me.coderfrish.ankair.server.network.login;

import me.coderfrish.ankair.core.chat.ChatMessageBuilder;
import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public record ClientBoundLoginDisconnectPacket(ChatMessageBuilder messageBuilder) implements Packet<ITCPLoginListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writeString(this.messageBuilder.build());
    }
}
