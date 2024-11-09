package xyz.ankairmc.ankair.server.packet.play;

import xyz.ankairmc.ankair.core.Identifier;
import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class C19PluginMessagePacket implements Packet<IPlayListener> {
    private final Identifier channel;
    private final PacketBuffer data;

    public C19PluginMessagePacket(Identifier channel, PacketBuffer data) {
        this.channel = channel;
        this.data = data;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeIdentifier(channel);
        data.writeBytes(this.data);
    }
}
