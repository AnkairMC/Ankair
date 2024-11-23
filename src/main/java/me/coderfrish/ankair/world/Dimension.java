package me.coderfrish.ankair.world;

public enum Dimension {
    NETHER(-1),
    OVERWORLD(0),
    THE_END(1);

    public final int id;

    Dimension(int id) {
        this.id = id;
    }
}
