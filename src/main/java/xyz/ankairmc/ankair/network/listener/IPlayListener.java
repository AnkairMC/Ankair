package xyz.ankairmc.ankair.network.listener;

import xyz.ankairmc.ankair.network.packet.PacketListener;
import xyz.ankairmc.ankair.server.packet.play.serverbound.S02PlayChatMessagePacket;
import xyz.ankairmc.ankair.server.packet.play.serverbound.S0EPlayKeepAlivePacket;
import xyz.ankairmc.ankair.server.packet.play.serverbound.S11PositionAndLookPacket;

public interface IPlayListener extends PacketListener {
    void handleKeepAlive(S0EPlayKeepAlivePacket packet);

    void handleChatMessage(S02PlayChatMessagePacket packet);

    void handlePlayerPositionAndLookPacket(S11PositionAndLookPacket packet);
}
