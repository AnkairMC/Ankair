package xyz.ankairmc.ankair.server.packet.handshake;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.protocol.ConnectionStatus;
import xyz.ankairmc.ankair.protocol.IHandshakeListener;
import xyz.ankairmc.ankair.protocol.types.UtfString;
import xyz.ankairmc.ankair.protocol.types.VarInt;

public class S00HandshakePacket implements Packet<IHandshakeListener> {
    public int protocol;
    public String host;
    public int port;
    public ConnectionStatus status;

    @Override
    public void read(ByteBuf data) {
        this.protocol = VarInt.read(data);
        this.host = UtfString.read(data);
        this.port = data.readUnsignedShort();
        this.status = VarInt.read(data) == 2 ? ConnectionStatus.LOGIN : ConnectionStatus.STATUS;
    }

    @Override
    public void listener(IHandshakeListener listener) {
        listener.handleHandshake(this);
    }
}
