package xyz.ankairmc.ankair.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import xyz.ankairmc.ankair.network.NetworkManager;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.packet.PacketDirection;
import xyz.ankairmc.ankair.packet.PacketListener;
import xyz.ankairmc.ankair.player.Player;
import xyz.ankairmc.ankair.protocol.types.VarInt;

import java.util.List;

public class MessagePacketDecoder extends MessageToMessageDecoder<ByteBuf> {
    private Player session;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.session = ctx.channel().attr(NetworkManager.session).get();
        super.channelActive(ctx);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> output) {
        int id = VarInt.read(input);
        ConnectionStatus status = session.getStatus();

        Class<? extends Packet<? extends PacketListener>> packetClazz = ConnectionStatus.getPacketById(status, PacketDirection.SERVER_BOUND, id);
        if (packetClazz != null) {
            try {
                Packet<? extends PacketListener> packet = packetClazz.getConstructor().newInstance();

                packet.read(input);
                output.add(packet);
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        }
    }
}
