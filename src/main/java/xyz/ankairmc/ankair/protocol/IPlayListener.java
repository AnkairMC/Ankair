package xyz.ankairmc.ankair.protocol;

import xyz.ankairmc.ankair.packet.PacketListener;
import xyz.ankairmc.ankair.server.packet.play.S02PlayChatMessagePacket;
import xyz.ankairmc.ankair.server.packet.play.S0BPlayKeepAlive;
import xyz.ankairmc.ankair.server.packet.play.S0EPositionAndLookPacket;

public interface IPlayListener extends PacketListener {
    void handleKeepAlive(S0BPlayKeepAlive packet);

    void handleChatMessage(S02PlayChatMessagePacket packet);

    void handlePlayerPositionAndLookPacket(S0EPositionAndLookPacket packet);
}
