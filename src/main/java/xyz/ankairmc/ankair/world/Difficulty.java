package xyz.ankairmc.ankair.world;

public enum Difficulty {
    PEACEFUL(0),
    EASY(1),
    NORMAL(2),
    HARD(3);

    public final int id;

    Difficulty(int id) {
        this.id = id;
    }
}
