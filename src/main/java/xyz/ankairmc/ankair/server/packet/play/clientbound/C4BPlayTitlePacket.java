package xyz.ankairmc.ankair.server.packet.play.clientbound;

import xyz.ankairmc.ankair.network.PacketBuffer;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.server.packet.play.chat.IChatComponent;
import xyz.ankairmc.ankair.server.packet.play.title.Action;

public class C4BPlayTitlePacket implements Packet<IPlayListener> {
    private final Action action;
    private final IChatComponent text;
    private final int fadeIn;
    private final int stay;
    private final int fadeOut;

    public C4BPlayTitlePacket(Action action, IChatComponent text, int fadeIn, int stay, int fadeOut) {
        this.action = action;
        this.text = text;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public C4BPlayTitlePacket(Action action, IChatComponent text) {
        this.action = action;
        this.text = text;
        this.fadeIn = 0;
        this.stay = 0;
        this.fadeOut = 0;
    }

    public C4BPlayTitlePacket(Action action, int fadeIn, int stay, int fadeOut) {
        this.action = action;
        this.text = IChatComponent.build();
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeVarInt(action.id);

        switch (action) {
            case SET_TITLE, SET_SUBTITLE, SET_ACTION_BAR -> data.writeChatComponent(text);
            case SET_TIMES_AND_DISPLAY -> {
                data.writeInt(fadeIn);
                data.writeInt(stay);
                data.writeInt(fadeOut);
            }
        }
    }
}
