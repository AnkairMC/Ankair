package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;
import me.coderfrish.ankair.player.Player;

public record ClientBoundGameJoinGamePacket(Player player, int max, int viewDistance, boolean debug) implements Packet<ITCPGameListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writeInt(player.getEntityId());
        data.writeByte(player.getGameMode().id);
        data.writeInt(player.getWorld().getDimension().id);

        data.writeByte(max);
        data.writeString(player.getWorld().getLevelType().name, 16);
        data.writeVarInt(viewDistance);
        data.writeBoolean(debug);
    }
}
