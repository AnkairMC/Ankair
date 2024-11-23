package me.coderfrish.ankair.player;

import me.coderfrish.ankair.entity.Entity;
import me.coderfrish.ankair.world.World;

public class Player extends Entity {
    private World world;
    private Location location = new Location(0, 0, 0, 0, 0);
    private GameMode gameMode;

    public Player(GameMode gameMode, World world) {
        this.gameMode = gameMode;
        this.world = world;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Location getLocation() {
        return location;
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
