package xyz.ankairmc.ankair.server.packet.play.clientbound;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;
import xyz.ankairmc.ankair.server.packet.play.chat.IChatComponent;
import xyz.ankairmc.ankair.server.packet.play.chat.ChatType;

public class C0EPlayChatMessagePacket implements Packet<IPlayListener> {
    private final ChatType type;
    private final IChatComponent component;

    public C0EPlayChatMessagePacket(ChatType type, IChatComponent component) {
        this.type = type;
        this.component = component;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeChatComponent(component);
        data.writeByte(type.id);
    }
}
