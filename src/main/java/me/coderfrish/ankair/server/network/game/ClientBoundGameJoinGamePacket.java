package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;
import me.coderfrish.ankair.player.Player;

public record ClientBoundGameJoinGamePacket(Player player, int max, int viewDistance, long hashSeed, boolean debug, boolean enableRespawnScreen) implements Packet<ITCPGameListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writeInt(player.getEntityId());
        data.writeByte(player.getGameMode().id);
        data.writeInt(player.getWorld().getDimension().id);

        data.writeLong(hashSeed);
        data.writeByte(max);
        data.writeString(player.getWorld().getLevelType().name, 16);
        data.writeVarInt(viewDistance);
        data.writeBoolean(debug);
        data.writeBoolean(enableRespawnScreen);
    }
}
