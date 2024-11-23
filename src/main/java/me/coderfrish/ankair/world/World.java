package me.coderfrish.ankair.world;

public class World {
    private Dimension dimension;
    private LevelType levelType;

    public World() {
        this.dimension = Dimension.OVERWORLD;
        this.levelType = LevelType.CUSTOMIZED;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
    }
}
