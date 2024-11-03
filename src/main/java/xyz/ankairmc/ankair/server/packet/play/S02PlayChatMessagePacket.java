package xyz.ankairmc.ankair.server.packet.play;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.types.UtfString;

public class S02PlayChatMessagePacket implements Packet<IPlayListener> {
    public String message;

    @Override
    public void read(ByteBuf data) {
        this.message = UtfString.read(data, 256);
    }

    @Override
    public void listener(IPlayListener listener) {
        listener.handleChatMessage(this);
    }
}
