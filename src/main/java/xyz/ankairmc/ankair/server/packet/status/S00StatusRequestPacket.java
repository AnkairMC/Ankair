package xyz.ankairmc.ankair.server.packet.status;

import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.IStatusListener;

public class S00StatusRequestPacket implements Packet<IStatusListener> {
    @Override
    public void listener(IStatusListener listener) {
        listener.handleStatusRequest(this);
    }
}
