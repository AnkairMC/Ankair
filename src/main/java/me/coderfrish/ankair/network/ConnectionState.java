package me.coderfrish.ankair.network;

import me.coderfrish.ankair.core.KeyValueHashMap;
import me.coderfrish.ankair.server.network.game.*;
import me.coderfrish.ankair.server.network.handshaking.ServerBoundHandshakingPacket;
import me.coderfrish.ankair.server.network.login.ClientBoundLoginDisconnectPacket;
import me.coderfrish.ankair.server.network.login.ClientBoundLoginSuccessPacket;
import me.coderfrish.ankair.server.network.login.ServerBoundLoginStartPacket;
import me.coderfrish.ankair.server.network.status.ServerBoundStatusPingPacket;
import me.coderfrish.ankair.server.network.status.ServerBoundStatusRequestPacket;
import me.coderfrish.ankair.server.network.status.ClientBoundStatusPongPacket;
import me.coderfrish.ankair.server.network.status.ClientBoundStatusResponsePacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public enum ConnectionState {
    HANDSHAKING {
        {
            createDirection(Direction.SERVER_BOUND, (map) -> {
                map.put(0x00, ServerBoundHandshakingPacket.class);
            });
        }
    },
    STATE {
        {
            createDirection(Direction.CLIENT_BOUND, (map) -> {
                map.put(0x00, ClientBoundStatusResponsePacket.class);
                map.put(0x01, ClientBoundStatusPongPacket.class);
            });

            createDirection(Direction.SERVER_BOUND, (map) -> {
                map.put(0x00, ServerBoundStatusRequestPacket.class);
                map.put(0x01, ServerBoundStatusPingPacket.class);
            });
        }
    },
    LOGIN {
        {
            createDirection(Direction.CLIENT_BOUND, (map) -> {
                map.put(0x00, ClientBoundLoginDisconnectPacket.class);
                map.put(0x02, ClientBoundLoginSuccessPacket.class);
            });

            createDirection(Direction.SERVER_BOUND, (map) -> {
                map.put(0x00, ServerBoundLoginStartPacket.class);
            });
        }
    },
    GAME {
        {
            createDirection(Direction.CLIENT_BOUND, (map) -> {
                map.put(0x26, ClientBoundGameJoinGamePacket.class);
                map.put(0x4E, ClientBoundGameSpawnPositionPacket.class);
                map.put(0x1B, ClientBoundGameDisconnectPacket.class);
                map.put(0x21, ClientBoundGameKeepAlivePacket.class);

                map.put(0x36, ClientBoundGamePositionAndLookPacket.class);
                map.put(0x19, ClientBoundGamePluginMessagePacket.class);
            });

            createDirection(Direction.SERVER_BOUND, (map) -> {
                map.put(0x0F, ServerBoundGameKeepAlivePacket.class);
                map.put(0x05, ServerBoundGameClientSettingsPacket.class);
                map.put(0x00, ServerBoundTeleportConfirmPacket.class);

                map.put(0x12, ServerBoundGamePlayerPositionRotationPacket.class);
            });
        }
    };

    public final Map<Direction, KeyValueHashMap<Integer, Class<? extends Packet<? extends PacketListener>>>> packets = new ConcurrentHashMap<>();

    public void createDirection(Direction direction, Consumer<KeyValueHashMap<Integer, Class<? extends Packet<? extends PacketListener>>>> callback) {
        KeyValueHashMap<Integer, Class<? extends Packet<? extends PacketListener>>> clazzMap = this.packets.get(direction);
        if (clazzMap == null) {
            this.packets.put(direction, new KeyValueHashMap<>());
            clazzMap = this.packets.get(direction);
        }

        callback.accept(clazzMap);
    }

    public static int getIdByPacketClazz(ConnectionState state, Class<? extends Packet<? extends PacketListener>> clazz) {
        KeyValueHashMap<Integer, Class<? extends Packet<? extends PacketListener>>> clazzMap = state.packets.get(Direction.CLIENT_BOUND);
        return clazzMap.getKey(clazz);
    }

    public static Class<? extends Packet<? extends PacketListener>> getPacketClazzById(ConnectionState state, int id) {
        return state.packets.getOrDefault(Direction.SERVER_BOUND, new KeyValueHashMap<>()).get(id);
    }

    public enum Direction {
        SERVER_BOUND,
        CLIENT_BOUND
    }
}
