package xyz.ankairmc.ankair.server.packet.play;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class C18PluginMessagePacket implements Packet<IPlayListener> {
    private final String channel;
    private final String data;

    public C18PluginMessagePacket(String channel, String data) {
        this.channel = channel;
        this.data = data;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeUtfString(channel, 20);
        data.writeUtfString(this.data);
    }
}
