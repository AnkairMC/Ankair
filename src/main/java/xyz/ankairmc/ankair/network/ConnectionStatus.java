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
            createPacket(0x23, PacketDirection.CLIENT_BOUND, C23PlayJoinGamePacket.class);
            createPacket(0x46, PacketDirection.CLIENT_BOUND, C46PlaySpawnPositionPacket.class);
            createPacket(0x2F, PacketDirection.CLIENT_BOUND, C2FPlayPositionAndLookPacket.class);

            createPacket(0x1A, PacketDirection.CLIENT_BOUND, C1APlayDisconnectPacket.class);

            createPacket(0x1F, PacketDirection.CLIENT_BOUND, C1FPlayKeepAlivePacket.class);
            createPacket(0x0B, PacketDirection.SERVER_BOUND, S0BPlayKeepAlive.class);

            createPacket(0x02, PacketDirection.SERVER_BOUND, S02PlayChatMessagePacket.class);
            createPacket(0x0F, PacketDirection.CLIENT_BOUND, C0FPlayChatMessagePacket.class);

            createPacket(0x4A, PacketDirection.CLIENT_BOUND, C4APlayTabListHeaderAndFooterPacket.class);
            createPacket(0x18, PacketDirection.CLIENT_BOUND, C18PluginMessagePacket.class);

            createPacket(0x0E, PacketDirection.SERVER_BOUND, S0EPositionAndLookPacket.class);
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
