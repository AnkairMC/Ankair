package xyz.ankairmc.ankair.server.packet.status.serverbound;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.listener.IStatusListener;

public class S00StatusRequestPacket implements Packet<IStatusListener> {
    @Override
    public void listener(IStatusListener listener) {
        listener.handleStatusRequest(this);
    }
}
