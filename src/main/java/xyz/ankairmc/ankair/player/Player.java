package xyz.ankairmc.ankair.player;

import io.netty.channel.Channel;
import xyz.ankairmc.ankair.MinecraftServer;
import xyz.ankairmc.ankair.core.GameProfile;
import xyz.ankairmc.ankair.entity.LivingEntity;
import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.packet.PacketListener;
import xyz.ankairmc.ankair.network.ConnectionStatus;
import xyz.ankairmc.ankair.server.packet.login.clientbound.C00LoginDisconnectPacket;
import xyz.ankairmc.ankair.server.packet.play.clientbound.C0EPlayChatMessagePacket;
import xyz.ankairmc.ankair.server.packet.play.clientbound.C1BPlayDisconnectPacket;
import xyz.ankairmc.ankair.server.packet.play.chat.ChatType;
import xyz.ankairmc.ankair.server.packet.play.chat.IChatComponent;

public class Player extends LivingEntity {
    private ConnectionStatus status = ConnectionStatus.HANDSHAKE;
    private final GameProfile gameProfile = new GameProfile();
    private GameMode gameMode = GameMode.SURVIVAL;
    private final Channel channel;
    private int protocol = -1;

    public Player(Channel channel) {
        this.channel = channel;
    }

    public void read(Packet<? extends PacketListener> packet) {
        try {
            listener(packet, status.getPacketListener().getConstructor(
                    Player.class
            ).newInstance(this));
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public void inActive() {
        MinecraftServer.removePlayer(this);
    }

    @SuppressWarnings("unchecked")
    private <T extends PacketListener> void listener(Packet<T> packet, PacketListener packetListener) {
        packet.listener((T) packetListener);
    }

    public void setStatus(ConnectionStatus status) {
        this.status = status;
    }

    public ConnectionStatus getStatus() {
        return status;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getProtocol() {
        return protocol;
    }

    public GameProfile getGameProfile() {
        return gameProfile;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void sendChatMessage(IChatComponent component) {
        this.sendPacket(new C0EPlayChatMessagePacket(
                ChatType.CHAT,
                component
        ));
    }

    public void sendChatMessage(String message) {
        this.sendChatMessage(IChatComponent.build().text(message));
    }
    
    public void disconnect(String reason) {
        if (status == ConnectionStatus.LOGIN) {
            this.sendPacket(new C00LoginDisconnectPacket(reason));
        } else if (status == ConnectionStatus.PLAY) {
            this.sendPacket(new C1BPlayDisconnectPacket(reason));
        }

        this.channel.disconnect();
    }

    public void disconnect() {
        this.disconnect("Unknown reason.");
    }

    public void sendPacket(Packet<? extends PacketListener> packet) {
        this.channel.writeAndFlush(packet);
    }
}
