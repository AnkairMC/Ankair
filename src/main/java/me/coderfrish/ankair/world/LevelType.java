package me.coderfrish.ankair.world;

public enum LevelType {
    DEFAULT("default"),
    FLAT("flat"),
    LARGE_BIOMES("largeBiomes"),
    AMPLIFIED("amplified"),
    CUSTOMIZED("customized"),
    BUFFET("buffet"),
    DEFAULT_1_1("default_1_1");

    public final String name;

    LevelType(String name) {
        this.name = name;
    }
}
