package xyz.ankairmc.ankair.server.packet.play;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;
import xyz.ankairmc.ankair.server.packet.play.chat.IChatComponent;

public class C4EPlayTabListHeaderAndFooterPacket implements Packet<IPlayListener> {
    private final IChatComponent header;
    private final IChatComponent footer;

    public C4EPlayTabListHeaderAndFooterPacket(IChatComponent header, IChatComponent footer) {
        this.header = header;
        this.footer = footer;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeUtfString(header.toString());
        data.writeUtfString(footer.toString());
    }
}
