package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public class ServerBoundTeleportConfirmPacket implements Packet<ITCPGameListener> {
    public int id;

    @Override
    public void read(PacketBuffer data) {
        this.id = data.readVarInt();
    }

    @Override
    public void listener(ITCPGameListener listener) {
        listener.handleTeleportConfirm(this);
    }
}
