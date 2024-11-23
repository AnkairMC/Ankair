package me.coderfrish.ankair.server.network.status;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;
import me.coderfrish.ankair.server.network.status.data.ServerStatusResponse;

public record ClientBoundStatusResponsePacket(ServerStatusResponse response) implements Packet<ITCPStatusListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writeString(response.toString());
    }
}
