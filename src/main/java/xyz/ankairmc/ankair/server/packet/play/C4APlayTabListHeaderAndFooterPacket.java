package xyz.ankairmc.ankair.server.packet.play;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.PacketBuffer;
import xyz.ankairmc.ankair.server.packet.play.chat.IChatComponent;

public class C4APlayTabListHeaderAndFooterPacket implements Packet<IPlayListener> {
    private final IChatComponent header;
    private final IChatComponent footer;

    public C4APlayTabListHeaderAndFooterPacket(IChatComponent header, IChatComponent footer) {
        this.header = header;
        this.footer = footer;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeUtfString(header.toString());
        data.writeUtfString(footer.toString());
    }
}
