package me.coderfrish.ankair.core;

import java.util.UUID;

public class GameProfile {
    private int protocol;
    private String username;
    private UUID uuid;

    public String getUsername() {
        return username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }
}
