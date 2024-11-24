package me.coderfrish.ankair.server.network.game;

import me.coderfrish.ankair.core.Flags;
import me.coderfrish.ankair.network.Packet;
import me.coderfrish.ankair.network.PacketBuffer;
import me.coderfrish.ankair.player.Location;

import java.util.Set;

public record ClientBoundGamePositionAndLookPacket(Location location, Set<Flags> set, int teleportId) implements Packet<ITCPGameListener> {
    @Override
    public void write(PacketBuffer data) {
        data.writeDouble(location.getX());
        data.writeDouble(location.getY());
        data.writeDouble(location.getZ());

        data.writeFloat(location.getYaw());
        data.writeFloat(location.getPitch());
        data.writeByte(Flags.pack(set));

        data.writeVarInt(teleportId);
    }
}
