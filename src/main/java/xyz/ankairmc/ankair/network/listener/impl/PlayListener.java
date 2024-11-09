package xyz.ankairmc.ankair.network.listener.impl;

import xyz.ankairmc.ankair.MinecraftServer;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.player.Player;
import xyz.ankairmc.ankair.server.packet.play.C0EPlayChatMessagePacket;
import xyz.ankairmc.ankair.server.packet.play.S02PlayChatMessagePacket;
import xyz.ankairmc.ankair.server.packet.play.S0EPlayKeepAlivePacket;
import xyz.ankairmc.ankair.server.packet.play.S11PositionAndLookPacket;
import xyz.ankairmc.ankair.server.packet.play.chat.ChatType;
import xyz.ankairmc.ankair.server.packet.play.chat.IChatComponent;

public class PlayListener implements IPlayListener {
    private final Player session;

    public PlayListener(Player session) {
        this.session = session;
    }

    @Override
    public void handleKeepAlive(S0EPlayKeepAlivePacket packet) {
    }

    @Override
    public void handleChatMessage(S02PlayChatMessagePacket packet) {
        for (Player player : MinecraftServer.getPlayers()) {
            player.sendPacket(new C0EPlayChatMessagePacket(ChatType.CHAT,
                    IChatComponent.build().text(session.getGameProfile().getUsername()+ " > " + packet.message))
            );
        }
    }

    @Override
    public void handlePlayerPositionAndLookPacket(S11PositionAndLookPacket packet) {
    }
}
