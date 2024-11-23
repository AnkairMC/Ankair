package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.core.BlockPosition;
import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;

public record ClientBoundGameSpawnPositionPacket(BlockPosition position) implements Packet<ITCPGameListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writePosition(position);
    }
}
