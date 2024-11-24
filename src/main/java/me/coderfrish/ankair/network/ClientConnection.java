package me.coderfrish.ankair.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;
import me.coderfrish.ankair.MinecraftServer;
import me.coderfrish.ankair.core.GameProfile;
import me.coderfrish.ankair.player.GameMode;
import me.coderfrish.ankair.player.Player;
import me.coderfrish.ankair.server.network.handshaking.TCPHandshakingListener;
import me.coderfrish.ankair.world.World;

public class ClientConnection extends SimpleChannelInboundHandler<Packet<? extends PacketListener>>{
    private ConnectionState state = ConnectionState.HANDSHAKING;
    private ScheduledFuture<?> keepAliveScheduledFuture;
    private PacketListener listener;
    private final Player player;

    private Channel channel;

    public ClientConnection() {
        this.listener = new TCPHandshakingListener(this);
        this.player = new Player(
                GameMode.SURVIVAL, new World()
        );
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.channel = ctx.channel();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        MinecraftServer.getServer().getPlayerList().removePlayer(this);
        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet<? extends PacketListener> packet) {
        this.readConnection(ctx, packet);
    }

    private void readConnection(ChannelHandlerContext ctx, Packet<? extends PacketListener> packet) {
        this.handlePacket(packet, listener);
    }

    public void sendPacket(Packet<? extends PacketListener> packet) {
        this.channel.writeAndFlush(packet);
    }

    public void sendPacket(Packet<? extends PacketListener> packet, GenericFutureListener<? extends Future<? super Void>> listener) {
        this.channel.writeAndFlush(packet).addListener(listener);
    }

    public void disconnect() {
        this.channel.disconnect();
    }

    public Channel getChannel() {
        return channel;
    }

    public Player getPlayer() {
        return player;
    }

    public ScheduledFuture<?> getKeepAliveScheduledFuture() {
        return keepAliveScheduledFuture;
    }

    public ConnectionState getState() {
        return state;
    }

    public void setListener(PacketListener listener) {
        this.listener = listener;
    }

    public void setState(ConnectionState state) {
        this.state = state;
    }

    public void setKeepAliveScheduledFuture(ScheduledFuture<?> keepAliveScheduledFuture) {
        this.keepAliveScheduledFuture = keepAliveScheduledFuture;
    }

    @SuppressWarnings("unchecked")
    private <L extends PacketListener> void handlePacket(Packet<L> packet, PacketListener listener) {
        packet.listener((L) listener);
    }

    public int getTeleportId() {
        return 1;
    }
}
