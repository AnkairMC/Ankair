package xyz.ankairmc.ankair.core;

import java.util.UUID;

public class GameProfile {
    private UUID uuid;
    private String username;
    private String displayName;
    private boolean hasDisplayName = false;

    public GameProfile() {
        this.displayName = getUsername();
    }

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isHasDisplayName() {
        return hasDisplayName;
    }

    public void setHasDisplayName(boolean hasDisplayName) {
        this.hasDisplayName = hasDisplayName;
    }

    @Override
    public String toString() {
        return "GameProfile{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                ", hasDisplayName=" + hasDisplayName +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
