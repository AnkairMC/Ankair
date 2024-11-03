package xyz.ankairmc.ankair.core;

import java.util.UUID;

public class GameProfile {
    private UUID uuid;
    private String username;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "GameProfile{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                '}';
    }
}
