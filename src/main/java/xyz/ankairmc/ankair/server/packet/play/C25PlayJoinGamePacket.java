package xyz.ankairmc.ankair.server.packet.play;

import xyz.ankairmc.ankair.network.packet.Packet;
import xyz.ankairmc.ankair.player.Difficulty;
import xyz.ankairmc.ankair.player.GameMode;
import xyz.ankairmc.ankair.network.listener.IPlayListener;
import xyz.ankairmc.ankair.network.PacketBuffer;

public class C25PlayJoinGamePacket implements Packet<IPlayListener> {
    private final int entityId;
    private final GameMode gameMode;
    private final boolean hardcore;
    private final int dimension;
    private final Difficulty difficulty;
    private final int max;
    private final String worldType;
    private final boolean debug;

    public C25PlayJoinGamePacket(int entityId, GameMode gameMode, boolean hardcore, int dimension, Difficulty difficulty, int max, String worldType, boolean debug) {
        this.entityId = entityId;
        this.gameMode = gameMode;
        this.hardcore = hardcore;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.max = max;
        this.worldType = worldType;
        this.debug = debug;
    }

    @Override
    public void write(PacketBuffer data) {
        data.writeInt(entityId);
        int i = gameMode.id;

        if (hardcore) {
            i |= 8;
        }
        data.writeByte(i);
        data.writeInt(dimension);
        data.writeByte(difficulty.id);
        data.writeByte(max);
        data.writeUtfString(worldType);
        data.writeBoolean(debug);
    }
}
