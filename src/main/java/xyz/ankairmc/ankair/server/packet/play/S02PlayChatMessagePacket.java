package xyz.ankairmc.ankair.server.packet.play;

import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.PacketBuffer;

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
