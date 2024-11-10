package xyz.ankairmc.ankair.network.listener;

import xyz.ankairmc.ankair.network.packet.PacketListener;
import xyz.ankairmc.ankair.server.packet.play.serverbound.*;

public interface IPlayListener extends PacketListener {
    void handleKeepAlive(S0EPlayKeepAlivePacket packet);

    void handleChatMessage(S02PlayChatMessagePacket packet);

    void handlePlayerPositionAndLook(S11PositionAndLookPacket packet);

    void handlePlayerPosition(S10PlayPlayerPositionPacket packet);

    void handlePlayerSteerVehicle(S1ASteerVehiclePacket packet);
}
