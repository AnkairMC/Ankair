package me.coderfrish.ankair.server.network.handshaking;

import me.coderfrish.ankair.network.ConnectionState;
import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public class ServerBoundHandshakingPacket implements Packet<ITCPHandshakingListener> {
    public int protocol, port;
    public String address;
    public ConnectionState nextState;

    @Override
    public void read(PacketBuffer data) {
        this.protocol = data.readVarInt();
        this.address = data.readString();
        this.port = data.readUnsignedShort();
        this.nextState = data.readVarInt() == 2 ? ConnectionState.LOGIN : ConnectionState.STATE;
    }

    @Override
    public void listener(ITCPHandshakingListener listener) {
        listener.handleHandshaking(this);
    }
}
