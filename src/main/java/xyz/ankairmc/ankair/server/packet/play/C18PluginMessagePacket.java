package xyz.ankairmc.ankair.server.packet.play;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.types.UtfString;

public class C18PluginMessagePacket implements Packet<IPlayListener> {
    private final String channel;
    private final String data;

    public C18PluginMessagePacket(String channel, String data) {
        this.channel = channel;
        this.data = data;
    }

    @Override
    public void write(ByteBuf data) {
        UtfString.write(data, channel, 20);
        UtfString.write(data, this.data);
    }
}
