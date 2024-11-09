package xyz.ankairmc.ankair.protocol.listener;

import io.netty.buffer.Unpooled;
import xyz.ankairmc.ankair.MinecraftServer;
import xyz.ankairmc.ankair.player.Difficulty;
import xyz.ankairmc.ankair.player.GameMode;
import xyz.ankairmc.ankair.player.Player;
import xyz.ankairmc.ankair.protocol.ConnectionStatus;
import xyz.ankairmc.ankair.protocol.ILoginListener;
import xyz.ankairmc.ankair.server.MinecraftCustom;
import xyz.ankairmc.ankair.server.MinecraftStatus;
import xyz.ankairmc.ankair.server.packet.login.C02LoginSuccessPacket;
import xyz.ankairmc.ankair.server.packet.login.S00LoginStartPacket;
import xyz.ankairmc.ankair.server.packet.play.*;
import xyz.ankairmc.ankair.server.packet.play.chat.ChatType;
import xyz.ankairmc.ankair.server.packet.play.chat.IChatComponent;

import java.awt.*;
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
        session.sendPacket(new C23PlayJoinGamePacket(
                session.getEntityId(),
                GameMode.CREATIVE,
                false,
                0,
                Difficulty.PEACEFUL,
                status.getMax(),
                "default",
                false
        ));
        session.sendPacket(new C46PlaySpawnPositionPacket(0, 0, 0));
        session.sendPacket(new C2FPlayPositionAndLookPacket(0, 0, 0, 0, 120, 0));
        session.sendPacket(new C18PluginMessagePacket("MC|Brand", ((MinecraftCustom) status).getServerModName()));

        session.sendPacket(new C4APlayTabListHeaderAndFooterPacket(
                IChatComponent.build().text("Ankair 1.12.2"),
                IChatComponent.build().text("Welcome ")
                        .extra(IChatComponent.build().text(session.getGameProfile().getUsername()).color("red"))
                        .extra(IChatComponent.build().text("!!"))
        ));
        session.getChannel().eventLoop().scheduleAtFixedRate(
                () -> session.sendPacket(
                        new C1FPlayKeepAlivePacket((int) (System.currentTimeMillis() / 10000))
                ),
                5, 10, TimeUnit.SECONDS);
        MinecraftServer.addPlayer(session);
    }
}
