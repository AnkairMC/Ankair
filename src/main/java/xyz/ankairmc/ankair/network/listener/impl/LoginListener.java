package xyz.ankairmc.ankair.network.listener.impl;

import io.netty.buffer.Unpooled;
import xyz.ankairmc.ankair.MinecraftServer;
import xyz.ankairmc.ankair.core.Identifier;
import xyz.ankairmc.ankair.network.PacketBuffer;
import xyz.ankairmc.ankair.network.listener.ILoginListener;
import xyz.ankairmc.ankair.server.packet.play.clientbound.*;
import xyz.ankairmc.ankair.server.packet.play.title.Action;
import xyz.ankairmc.ankair.world.Difficulty;
import xyz.ankairmc.ankair.player.Player;
import xyz.ankairmc.ankair.network.ConnectionStatus;
import xyz.ankairmc.ankair.server.MinecraftCustom;
import xyz.ankairmc.ankair.server.MinecraftStatus;
import xyz.ankairmc.ankair.server.packet.login.clientbound.C02LoginSuccessPacket;
import xyz.ankairmc.ankair.server.packet.login.serverbound.S00LoginStartPacket;
import xyz.ankairmc.ankair.server.packet.play.chat.IChatComponent;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LoginListener implements ILoginListener {
    private final Player session;

    public LoginListener(Player session) {
        this.session = session;
    }

    @Override
    public void handleLoginStart(S00LoginStartPacket packet) {
        if (!packet.username.matches("[a-zA-Z0-9]{3,16}")) {
            session.disconnect("Bad Username: " + packet.username);
            return;
        }

        session.getGameProfile().setUsername(packet.username);
        session.getGameProfile().setUuid(UUID.nameUUIDFromBytes(
                String.format("OfflinePlayer:%s", session.getGameProfile().getUsername())
                        .getBytes(StandardCharsets.UTF_8)
        ));

        MinecraftStatus status = MinecraftServer.getServer();
        if (session.getProtocol() != status.getProtocol()) {
            session.disconnect("Bad Protocol: " + session.getProtocol());
            return;
        }

        if (MinecraftServer.getPlayers().size() + 1 > MinecraftServer.getServer().getMax()) {
            session.disconnect("There are too many people.");
            return;
        }

        session.sendPacket(new C02LoginSuccessPacket(
                session.getGameProfile().getUuid(), session.getGameProfile().getUsername()
        ));
        session.setStatus(ConnectionStatus.PLAY);
        session.sendPacket(new C25PlayJoinGamePacket(
                session.getEntityId(),
                session.getGameMode(),
                false,
                0,
                Difficulty.PEACEFUL,
                status.getMax(),
                "default",
                false
        ));
        session.sendPacket(new C49PlaySpawnPositionPacket(0, 0, 0));
        session.sendPacket(new C32PlayPositionAndLookPacket(0, 0, 0, 0, 120, 0));
        session.sendPacket(new C19PlayPluginMessagePacket(
                new Identifier("brand"),
                new PacketBuffer(
                        Unpooled.buffer()
                ).writeUtfString(((MinecraftCustom) status).getServerModName())
        ));

        session.sendPacket(new C4EPlayTabListHeaderAndFooterPacket(
                IChatComponent.build().text("Ankair 1.13.2"),
                IChatComponent.build().text("Welcome ")
                        .extra(IChatComponent.build().text(session.getGameProfile().getUsername()).color("red"))
                        .extra(IChatComponent.build().text("!!"))
        ));
        session.getChannel().eventLoop().scheduleAtFixedRate(
                () -> session.sendPacket(
                        new C21PlayKeepAlivePacket((int) (System.currentTimeMillis() / 10000))
                ),
                5, 10, TimeUnit.SECONDS);
        MinecraftServer.addPlayer(session);
    }
}
