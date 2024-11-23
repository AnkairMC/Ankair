package me.coderfrish.ankair.player;

import me.coderfrish.ankair.core.GameProfile;
import me.coderfrish.ankair.entity.Entity;
import me.coderfrish.ankair.world.World;

public class Player extends Entity {
    private final GameProfile profile;
    private World world;
    private Location location = new Location(0, 0, 0, 0, 0);
    private GameMode gameMode;

    public Player(GameMode gameMode, World world) {
        this.gameMode = gameMode;
        this.world = world;
        this.profile = new GameProfile();
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Location getLocation() {
        return location;
    }

    public GameProfile getProfile() {
        return profile;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
