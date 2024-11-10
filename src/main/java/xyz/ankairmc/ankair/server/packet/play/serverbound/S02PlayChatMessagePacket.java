package xyz.ankairmc.ankair.server.packet.play.serverbound;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class S02PlayChatMessagePacket implements Packet<IPlayListener> {
    public String message;

    @Override
    public void read(PacketBuffer data) {
        this.message = data.readUtfString(256);
    }

    @Override
    public void listener(IPlayListener listener) {
        listener.handleChatMessage(this);
    }
}
