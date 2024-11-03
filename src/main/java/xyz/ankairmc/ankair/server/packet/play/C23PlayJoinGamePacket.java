package xyz.ankairmc.ankair.server.packet.play;

import io.netty.buffer.ByteBuf;
import xyz.ankairmc.ankair.packet.Packet;
import xyz.ankairmc.ankair.player.Difficulty;
import xyz.ankairmc.ankair.player.GameMode;
import xyz.ankairmc.ankair.protocol.IPlayListener;
import xyz.ankairmc.ankair.protocol.types.UtfString;

public class C23PlayJoinGamePacket implements Packet<IPlayListener> {
    private final int entityId;
    private final GameMode gameMode;
    private final boolean hardcore;
    private final int dimension;
    private final Difficulty difficulty;
    private final int max;
    private final String worldType;
    private final boolean debug;

    public C23PlayJoinGamePacket(int entityId, GameMode gameMode, boolean hardcore, int dimension, Difficulty difficulty, int max, String worldType, boolean debug) {
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
    public void write(ByteBuf data) {
        data.writeInt(entityId);
        int i = gameMode.id;

        if (hardcore) {
            i |= 8;
        }
        data.writeByte(i);
        data.writeInt(dimension);
        data.writeByte(difficulty.id);
        data.writeByte(max);
        UtfString.write(data, worldType);
        data.writeBoolean(debug);
    }
}
