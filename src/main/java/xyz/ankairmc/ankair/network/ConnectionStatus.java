package xyz.ankairmc.ankair.network;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.network.packet.PacketDirection;
import xyz.ankairmc.ankair.network.packet.PacketListener;
import xyz.ankairmc.ankair.network.listener.impl.HandshakeListener;
import xyz.ankairmc.ankair.network.listener.impl.LoginListener;
import xyz.ankairmc.ankair.network.listener.impl.PlayListener;
import xyz.ankairmc.ankair.network.listener.impl.StatusListener;
import xyz.ankairmc.ankair.server.packet.handshake.S00HandshakePacket;
import xyz.ankairmc.ankair.server.packet.login.C00LoginDisconnectPacket;
import xyz.ankairmc.ankair.server.packet.login.C02LoginSuccessPacket;
import xyz.ankairmc.ankair.server.packet.login.S00LoginStartPacket;
import xyz.ankairmc.ankair.server.packet.play.*;
import xyz.ankairmc.ankair.server.packet.status.S01StatusPingPacket;
import xyz.ankairmc.ankair.server.packet.status.S00StatusRequestPacket;
import xyz.ankairmc.ankair.server.packet.status.C00StatusResponsePacket;
import xyz.ankairmc.ankair.server.packet.status.C01StatusPongPacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ConnectionStatus {
    HANDSHAKE(HandshakeListener.class) {
        {
            createPacket(0x00, PacketDirection.SERVER_BOUND, S00HandshakePacket.class);
        }
    },
    STATUS(StatusListener.class) {
        {
            createPacket(0x00, PacketDirection.SERVER_BOUND, S00StatusRequestPacket.class);
            createPacket(0x00, PacketDirection.CLIENT_BOUND, C00StatusResponsePacket.class);

            createPacket(0x01, PacketDirection.SERVER_BOUND, S01StatusPingPacket.class);
            createPacket(0x01, PacketDirection.CLIENT_BOUND, C01StatusPongPacket.class);
        }
    },
    LOGIN(LoginListener.class) {
        {
            createPacket(0x00, PacketDirection.SERVER_BOUND, S00LoginStartPacket.class);
            createPacket(0x00, PacketDirection.CLIENT_BOUND, C00LoginDisconnectPacket.class);

            createPacket(0x02, PacketDirection.CLIENT_BOUND, C02LoginSuccessPacket.class);
        }
    },
    PLAY(PlayListener.class) {
        {
            createPacket(0x25, PacketDirection.CLIENT_BOUND, C25PlayJoinGamePacket.class);
            createPacket(0x49, PacketDirection.CLIENT_BOUND, C49PlaySpawnPositionPacket.class);
            createPacket(0x32, PacketDirection.CLIENT_BOUND, C32PlayPositionAndLookPacket.class);

            createPacket(0x1B, PacketDirection.CLIENT_BOUND, C1BPlayDisconnectPacket.class);

            createPacket(0x21, PacketDirection.CLIENT_BOUND, C21PlayKeepAlivePacket.class);
            createPacket(0x0E, PacketDirection.SERVER_BOUND, S0EPlayKeepAlivePacket.class);

            createPacket(0x02, PacketDirection.SERVER_BOUND, S02PlayChatMessagePacket.class);
            createPacket(0x0E, PacketDirection.CLIENT_BOUND, C0EPlayChatMessagePacket.class);

            createPacket(0x4E, PacketDirection.CLIENT_BOUND, C4EPlayTabListHeaderAndFooterPacket.class);
            createPacket(0x19, PacketDirection.CLIENT_BOUND, C19PluginMessagePacket.class);

            createPacket(0x11, PacketDirection.SERVER_BOUND, S11PositionAndLookPacket.class);
        }
    };

    final Class<? extends PacketListener> packetListener;

    ConnectionStatus(Class<? extends PacketListener> packetListener) {
        this.packetListener = packetListener;
    }

    public Class<? extends PacketListener> getPacketListener() {
        return packetListener;
    }

    final Map<PacketDirection, Map<Integer, Class<? extends Packet<? extends PacketListener>>>> packetMap = Map.of(
            PacketDirection.CLIENT_BOUND, new ConcurrentHashMap<>(),
            PacketDirection.SERVER_BOUND, new ConcurrentHashMap<>()
    );

    public void createPacket(int id, PacketDirection direction, Class<? extends Packet<? extends PacketListener>> packet) {
        packetMap.get(direction).put(id, packet);
    }

    public static Class<? extends Packet<? extends PacketListener>> getPacketById(ConnectionStatus status, PacketDirection direction, int id) {
        return status.packetMap.getOrDefault(direction, new ConcurrentHashMap<>()).get(id);
    }

    public static int getIdByPacket(ConnectionStatus status, PacketDirection direction, Class<? extends Packet<? extends PacketListener>> packet) {
        Map<Integer, Class<? extends Packet<? extends PacketListener>>> packetMap = status.packetMap.get(direction);
        for (int i : packetMap.keySet()) {
            if (packetMap.get(i) != null && packetMap.get(i) == packet) {
                return i;
            }
        }

        return 0x00;
    }
}
