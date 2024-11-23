package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;
import me.coderfrish.ankair.player.Location;

public record ClientBoundGamePositionAndLookPacket(Location location, byte flags, int teleportId) implements Packet<ITCPGameListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writeDouble(location.getX());
        data.writeDouble(location.getY());
        data.writeDouble(location.getZ());

        data.writeFloat(location.getYaw());
        data.writeFloat(location.getPitch());
        data.writeByte(flags);

        data.writeVarInt(teleportId);
    }
}
