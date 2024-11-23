package me.coderfrish.ankair.player;

public enum GameMode {
    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3);

    public final byte id;

    GameMode(int id) {
        this.id = (byte) id;
    }
}
