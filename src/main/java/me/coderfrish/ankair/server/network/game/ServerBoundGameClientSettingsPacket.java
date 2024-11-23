package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public class ServerBoundGameClientSettingsPacket implements Packet<ITCPGameListener> {
    public String locale;
    public byte viewDistance;
    public int chatMode;
    public boolean chatColors;
    public byte displaySkin;
    public int mainHand;

    @Override
    public void read(PacketBuffer data) {
        this.locale = data.readString(16);
        this.viewDistance = data.readByte();
        this.chatMode = data.readVarInt();
        this.chatColors = data.readBoolean();
        this.displaySkin = (byte) data.readUnsignedByte();
        this.mainHand = data.readVarInt();
    }

    @Override
    public void listener(ITCPGameListener listener) {
        listener.handleClientSettings(this);
    }
}
